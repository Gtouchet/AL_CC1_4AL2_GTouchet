package esgi.al.cc1.application;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import esgi.al.cc1.infrastructure.repositories.EntityNotFoundException;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class WorkerServiceImpl implements WorkerService
{
    private final Repository<Worker> workerRepository;
    private final Repository<Project> projectRepository;
    private final Repository<Contractor> contractorRepository;

    public WorkerServiceImpl(
            Repository<Worker> workerRepository,
            Repository<Contractor> contractorRepository,
            Repository<Project> projectRepository)
    {
        this.workerRepository = workerRepository;
        this.contractorRepository = contractorRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Id create(String login, Password password, String name, Service service, int department)
    {
        if (this.contractorRepository.read().anyMatch(contractor -> contractor.getLogin().equals(login)) ||
            this.workerRepository.read().anyMatch(worker -> worker.getLogin().equals(login)))
        {
            System.out.println("Error: login already in use");
            return null;
        }

        Worker worker = Worker.of(
                Id.generate(),
                login,
                password,
                name,
                service,
                department,
                Date.now()
        );
        this.workerRepository.create(worker);
        return worker.getId();
    }

    @Override
    public void read()
    {
        List<Worker> workers = this.workerRepository.read().collect(Collectors.toList());

        if (workers.size() == 0)
        {
            System.out.println("No Worker registered yet");
        }
        else
        {
            workers.forEach(System.out::println);
        }
    }

    @Override
    public void read(Id id)
    {
        try {
            System.out.println(this.workerRepository.read(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Id id, Password password, String name, Service service, int department)
    {
        try {
            Worker worker = this.workerRepository.read(id);

            this.workerRepository.update(id, Worker.of(
                    worker.getId(),
                    worker.getLogin(),
                    password,
                    name,
                    service,
                    department,
                    worker.getCreationDate()
            ));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Id id)
    {
        try {
            this.workerRepository.remove(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Remove the deleted worker from all projects
        this.projectRepository.read()
                .filter(project -> project.getWorkersId().contains(id))
                .collect(Collectors.toList())
                .forEach(project -> {
                    project.getWorkersId().remove(id);
                    try {
                        this.projectRepository.update(project.getId(), Project.of(
                                project.getId(),
                                project.getContractorId(),
                                project.getDepartment(),
                                project.getCreationDate()));
                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    @Override
    public long getRepositorySize()
    {
        return this.workerRepository.read().count();
    }

    @Override
    public boolean exists(Id id)
    {
        return this.workerRepository.exists(id);
    }
}
