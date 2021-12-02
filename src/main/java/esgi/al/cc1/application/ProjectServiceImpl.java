package esgi.al.cc1.application;

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

        Project project = Project.of(
                Id.generate(),
                contractorId,
                department,
                Date.now()
        );
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

            this.projectRepository.update(id, Project.of(
                    project.getId(),
                    contractorId,
                    department,
                    project.getCreationDate()
            ));
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
        Worker worker;
        try {
            worker = this.workerRepository.read(workerId);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            Project project = this.projectRepository.read(projectId);

            if (project.getWorkersId().contains(workerId))
            {
                System.out.println("Error: Worker ID [" + workerId + "] is already working on project [" + projectId + "]");
                return;
            }

            project.addWorker(worker);
            this.projectRepository.update(projectId, project);

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void fireWorker(Id projectId, Id workerId)
    {
        Worker worker;
        try {
            worker = this.workerRepository.read(workerId);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            Project project = this.projectRepository.read(projectId);

            if (project.getWorkersId().contains(workerId))
            {
                System.out.println("Error: Worker ID [" + workerId + "] is already working on project [" + projectId + "]");
                return;
            }

            project.removeWorker(worker);
            this.projectRepository.update(projectId, project);

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
