package al.cc2.gtouchet.application.services.worker;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.builders.ProjectBuilder;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteWorkerServiceHandler implements ServiceHandler<Boolean, Id>
{
    private final Repository<Worker> workerRepository;
    private final Repository<Project> projectRepository;

    public DeleteWorkerServiceHandler(
            Repository<Worker> workerRepository,
            Repository<Project> projectRepository)
    {
        this.workerRepository = workerRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Boolean handle(Id command)
    {
        try {
            this.workerRepository.remove(command);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Remove the deleted worker from all projects
        List<Project> projectsContainingWorker = this.projectRepository.read()
                .filter(project -> project.getWorkersId().contains(command))
                .collect(Collectors.toList());

        projectsContainingWorker.forEach(project ->
        {
            project.getWorkersId().remove(command);
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
