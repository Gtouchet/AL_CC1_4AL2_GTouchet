package esgi.al.gtouchet.cc2.application.services.project.fireWorker;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.builders.ProjectBuilder;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

import java.util.List;

public class FireWorkerServiceHandler implements ServiceHandler<Project, FireWorkerDto>
{
    private final Repository<Project> projectRepository;
    private final Repository<Worker> workerRepository;

    public FireWorkerServiceHandler(
            Repository<Project> projectRepository,
            Repository<Worker> workerRepository)
    {
        this.projectRepository = projectRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public Project handle(FireWorkerDto command)
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
