package al.cc2.gtouchet.application.services.handlers.project;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.project.EngageFireWorkerCommand;
import al.cc2.gtouchet.domain.builders.ProjectBuilder;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;

public class FireWorkerCommandHandler implements CommandHandler<Project, EngageFireWorkerCommand>
{
    private final Repository<Project> projectRepository;
    private final Repository<Worker> workerRepository;

    public FireWorkerCommandHandler(
            Repository projectRepository,
            Repository workerRepository)
    {
        this.projectRepository = projectRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public Project handle(EngageFireWorkerCommand command)
    {
        try {
            Project project = this.projectRepository.read(command.projectId);

            if (!this.workerRepository.exists(command.workerId))
            {
                System.out.println("Error: no Worker registered with ID [" + command.workerId + "]");
                return null;
            }

            List<Id> workersId = project.getWorkersId();

            if (!workersId.remove(command.workerId))
            {
                System.out.println("Error: Worker ID [" + command.workerId + "] is not working on project [" + command.projectId + "]");
                return null;
            }

            project = ProjectBuilder.init(project)
                    .setWorkersId(workersId)
                    .build();

            this.projectRepository.update(command.projectId, project);

            return project;

        } catch (EntityNotFoundException | NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
