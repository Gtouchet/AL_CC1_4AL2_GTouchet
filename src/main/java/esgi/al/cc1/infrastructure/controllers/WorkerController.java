package esgi.al.cc1.infrastructure.controllers;

import esgi.al.cc1.domain.dtos.Date;
import esgi.al.cc1.domain.dtos.Id;
import esgi.al.cc1.domain.dtos.Password;
import esgi.al.cc1.domain.enumerators.Service;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.stream.Stream;

public class WorkerController implements Controller<Worker>
{
    private final Repository<Worker> workerRepository;
    private final Repository<Project> projectRepository;

    public WorkerController(
            Repository<Worker> workerRepository,
            Repository<Project> projectRepository)
    {
        this.workerRepository = workerRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void create(String[] values)
    {
        try {
            Service service = Service.valueOf(values[4].toLowerCase());
            int department = Integer.parseInt(values[5]);

            this.workerRepository.create(Worker.of(
                    Id.generate(),
                    values[1],
                    Password.of(values[2]),
                    values[3],
                    service,
                    department,
                    Date.now()
            ));
        } catch (NumberFormatException e) {
            System.out.println("Error: impossible to parse [" + values[5] + "] as a department number");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown service type [" + values[4] + "]");
        } catch (FailedToCreate e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Stream<Worker> read()
    {
        return this.workerRepository.read();
    }

    @Override
    public Worker read(String id)
    {
        try {
            return this.workerRepository.read(id);
        } catch (ElementNotFound e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(String[] values)
    {
        try {
            Service service = Service.valueOf(values[4].toLowerCase());
            int department = Integer.parseInt(values[5]);

            this.workerRepository.update(values[1].toLowerCase(), Worker.of(
                    null,
                    null,
                    Password.of(values[2]),
                    values[3],
                    service,
                    department,
                    null
            ));
        } catch (NumberFormatException e) {
            System.out.println("Error: impossible to parse [" + values[5] + "] as a department number");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown service type [" + values[4] + "]");
        } catch (ElementNotFound | FailedToUpdate e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(String id)
    {
        try {
            this.workerRepository.remove(id);

            // Remove the deleted worker from all projects
            this.projectRepository.read().forEach(project -> {
                for (int i = 0; i < project.getWorkersId().size(); i += 1) {
                    if (project.getWorkersId().get(i).toString().equals(id)) {
                        try {
                            this.projectRepository.removeWorker(project.getId().toString(), id);
                            break;
                        } catch (ElementNotFound | FailedToUpdate e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            });
        } catch (ElementNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void validatePayment(String id)
    {
        // Do nothing
    }

    @Override
    public void addWorker(String[] values)
    {
        // Do nothing
    }

    @Override
    public void removeWorker(String[] values)
    {
        // Do nothing
    }
}
