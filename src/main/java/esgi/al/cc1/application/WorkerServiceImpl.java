package esgi.al.cc1.application;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import esgi.al.cc1.infrastructure.repositories.ElementNotFoundException;
import esgi.al.cc1.infrastructure.repositories.FailedToCreateException;
import esgi.al.cc1.infrastructure.repositories.FailedToUpdateException;
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
    public void create(String login, Password password, String name, Service service, int department)
    {
        Contractor registeredContractor = this.contractorRepository.read()
                .filter(worker -> worker.getLogin().equals(login))
                .findFirst()
                .orElse(null);
        if (registeredContractor != null)
        {
            System.out.println("Error: login already in use by user ID [" + registeredContractor.getId() + "]");
            return;
        }

        try {
            this.workerRepository.create(Worker.of(
                    Id.generate(),
                    login,
                    password,
                    name,
                    service,
                    department,
                    Date.now()
            ));
        } catch (FailedToCreateException e) {
            System.out.println(e.getMessage());
        }
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
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Id id, Password password, String name, Service service, int department)
    {
        Worker worker;
        try {
            worker = this.workerRepository.read(id);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            this.workerRepository.update(id, Worker.of(
                    worker.getId(),
                    worker.getLogin(),
                    password,
                    name,
                    service,
                    department,
                    worker.getCreationDate()
            ));
        } catch (ElementNotFoundException | FailedToUpdateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Id id)
    {
        try {
            this.workerRepository.remove(id);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Remove the deleted worker from all registered projects
        this.projectRepository.read().forEach(project -> {
            project.getWorkersId().removeIf(workerId -> workerId.equals(id));
        });
    }
}
