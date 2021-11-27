package esgi.al.cc1.infrastructure.controllers;

import esgi.al.cc1.domain.dtos.Password;
import esgi.al.cc1.domain.enumerators.Service;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.stream.Stream;

public class WorkerController implements Controller<Worker>
{
    private final Repository<Worker> workerRepository;

    public WorkerController(Repository<Worker> workerRepository)
    {
        this.workerRepository = workerRepository;
    }

    // Todo: implements validators
    @Override
    public void create(String[] values) throws FailedToCreate
    {
        try {
            int department = Integer.parseInt(values[5]);
            try {
                Service service = Service.valueOf(values[4]);
                this.workerRepository.create(Worker.of(values[1], Password.of(values[2]), values[3], service, department));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: unknown service type [" + values[4] + "]");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: impossible to parse [" + values[5] + "] as a department number");
        }
    }

    @Override
    public Stream<Worker> read()
    {
        return this.workerRepository.read();
    }

    @Override
    public Worker read(String id) throws ElementNotFound
    {
        return this.workerRepository.read(id);
    }

    @Override
    public void update(Worker element) throws ElementNotFound, FailedToUpdate
    {

    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.workerRepository.remove(id);
    }
}
