package esgi.al.cc1.application;

import esgi.al.cc1.domain.builders.ProjectBuilder;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.repositories.EntityNotFoundException;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectServiceImpl implements ProjectService
{
    private final Repository<Project> projectRepository;
    private final Repository<Worker> workerRepository;
    private final Repository<Contractor> contractorRepository;

    public ProjectServiceImpl(
            Repository<Project> projectRepository,
            Repository<Contractor> contractorRepository,
            Repository<Worker> workerRepository)
    {
        this.projectRepository = projectRepository;
        this.workerRepository = workerRepository;
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Id create(Id contractorId, int department)
    {
        if(!this.contractorRepository.exists(contractorId))
        {
            System.out.println("Error: no Contractor registered with ID [" + contractorId + "]");
            return null;
        }

        Project project = ProjectBuilder.init(Id.generate(), Date.now())
                .setContractorId(contractorId)
                .setDepartment(department)
                .build();

        this.projectRepository.create(project);
        return project.getId();
    }

    @Override
    public void read()
    {
        List<Project> projects = this.projectRepository.read().collect(Collectors.toUnmodifiableList());

        if (projects.size() == 0)
        {
            System.out.println("No Project registered yet");
        }
        else
        {
            projects.forEach(System.out::println);
        }
    }

    @Override
    public void read(Id id)
    {
        try {
            this.projectRepository.read(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Id id, Id contractorId, int department)
    {
        if(!this.contractorRepository.exists(contractorId))
        {
            System.out.println("Error: no Contractor registered with ID [" + contractorId + "]");
            return;
        }

        try {
            Project project = this.projectRepository.read(id);

            project = ProjectBuilder.init(project)
                    .setContractorId(contractorId)
                    .setDepartment(department)
                    .build();

            this.projectRepository.update(id, project);

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Id id)
    {
        try {
            this.projectRepository.remove(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public long getRepositorySize()
    {
        return this.projectRepository.read().count();
    }

    @Override
    public boolean exists(Id id)
    {
        return this.projectRepository.exists(id);
    }

    @Override
    public void engageWorker(Id projectId, Id workerId)
    {
        if (!this.workerRepository.exists(workerId))
        {
            System.out.println("Error: no Worker registered with ID [" + workerId + "]");
            return;
        }

        try {
            Project project = this.projectRepository.read(projectId);
            List<Id> workersId = project.getWorkersId();

            if (project.getWorkersId().contains(workerId))
            {
                System.out.println("Error: Worker ID [" + workerId + "] is already working on project [" + projectId + "]");
                return;
            }

            workersId.add(workerId);

            project = ProjectBuilder.init(project)
                    .setWorkersId(workersId)
                    .build();

            this.projectRepository.update(projectId, project);

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void fireWorker(Id projectId, Id workerId)
    {
        if (!this.workerRepository.exists(workerId))
        {
            System.out.println("Error: no Worker registered with ID [" + workerId + "]");
            return;
        }

        try {
            Project project = this.projectRepository.read(projectId);
            List<Id> workersId = project.getWorkersId();

            if (!workersId.remove(workerId))
            {
                System.out.println("Error: Worker ID [" + workerId + "] is not working on project [" + projectId + "]");
                return;
            }

            project = ProjectBuilder.init(project)
                    .setWorkersId(workersId)
                    .build();

            this.projectRepository.update(projectId, project);

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
