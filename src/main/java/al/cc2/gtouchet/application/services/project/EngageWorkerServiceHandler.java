package al.cc2.gtouchet.application.services.project;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.project.dtos.EngageFireWorkerDto;
import al.cc2.gtouchet.domain.builders.ProjectBuilder;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;

public class EngageWorkerServiceHandler implements ServiceHandler<Project, EngageFireWorkerDto>
{
    private final Repository<Project> projectRepository;
    private final Repository<Worker> workerRepository;

    public EngageWorkerServiceHandler(
            Repository<Project> projectRepository,
            Repository<Worker> workerRepository)
    {
        this.projectRepository = projectRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public Project handle(EngageFireWorkerDto command)
    {
        try {
            Project project = this.projectRepository.read(command.projectId);

            if (!this.workerRepository.exists(command.workerId))
            {
                System.out.println("Error: no Worker registered with ID [" + command.workerId + "]");
                return null;
            }

            List<Id> workersId = project.getWorkersId();

            if (project.getWorkersId().contains(command.workerId))
            {
                System.out.println("Error: Worker ID [" + command.workerId + "] is already working on project [" + command.projectId + "]");
                return null;
            }

            workersId.add(command.workerId);

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
