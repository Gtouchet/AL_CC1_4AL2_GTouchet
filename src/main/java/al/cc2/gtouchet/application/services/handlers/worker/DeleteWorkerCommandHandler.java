package al.cc2.gtouchet.application.services.handlers.worker;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.worker.DeleteWorkerCommand;
import al.cc2.gtouchet.domain.builders.ProjectBuilder;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public final class DeleteWorkerCommandHandler implements CommandHandler<Boolean, DeleteWorkerCommand>
{
    private final Repository<Worker> workerRepository;
    private final Repository<Project> projectRepository;

    public DeleteWorkerCommandHandler(
            Repository workerRepository,
            Repository projectRepository)
    {
        this.workerRepository = workerRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Boolean handle(DeleteWorkerCommand command)
    {
        try {
            this.workerRepository.remove(command.id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Remove the deleted worker from all projects
        List<Project> projectsContainingWorker = this.projectRepository.read()
                .filter(project -> project.getWorkersId().contains(command.id))
                .collect(Collectors.toList());

        projectsContainingWorker.forEach(project ->
        {
            project.getWorkersId().remove(command.id);
            try {
                project = ProjectBuilder.init(project).build();
                this.projectRepository.update(project.getId(), project);

            } catch (NullPointerException | EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });

        return true;
    }
}
