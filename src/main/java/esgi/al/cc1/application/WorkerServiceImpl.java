package esgi.al.cc1.application;

import esgi.al.cc1.domain.builders.ProjectBuilder;
import esgi.al.cc1.domain.builders.WorkerBuilder;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.validators.PasswordFormatException;
import esgi.al.cc1.domain.validators.PasswordValidator;
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
    private final PasswordValidator passwordValidator;

    public WorkerServiceImpl(
            Repository<Worker> workerRepository,
            Repository<Contractor> contractorRepository,
            Repository<Project> projectRepository,
            PasswordValidator passwordValidator)
    {
        this.workerRepository = workerRepository;
        this.contractorRepository = contractorRepository;
        this.projectRepository = projectRepository;
        this.passwordValidator = passwordValidator;
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

        try {
            this.passwordValidator.validate(password);
        } catch (PasswordFormatException e) {
            System.out.println(e.getMessage());
            return null;
        }

        Worker worker;

        try {
            worker = WorkerBuilder.init(Id.generate(), login, Date.now())
                    .setPassword(password)
                    .setName(name)
                    .setService(service)
                    .setDepartment(department)
                    .build();

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }

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
        Worker worker;

        try {
            worker = this.workerRepository.read(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            this.passwordValidator.validate(password);
        } catch (PasswordFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            worker = WorkerBuilder.init(worker)
                    .setPassword(password)
                    .setName(name)
                    .setService(service)
                    .setDepartment(department)
                    .build();

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            this.workerRepository.update(id, worker);
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
                        List<Id> workersId = project.getWorkersId();
                        workersId.remove(id);

                        project = ProjectBuilder.init(project)
                                .setWorkersId(workersId)
                                .build();

                        this.projectRepository.update(project.getId(), project);

                    } catch (EntityNotFoundException | NullPointerException e) {
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
