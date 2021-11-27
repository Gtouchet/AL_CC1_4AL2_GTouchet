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

    // Todo: implements validators in all controller implementations
    @Override
    public void create(String[] values) throws FailedToCreate
    {
        try {
            Service service = Service.valueOf(values[4]);
            try {
                int department = Integer.parseInt(values[5]);
                this.workerRepository.create(Worker.of(
                        null,
                        values[1],
                        Password.of(values[2]),
                        values[3],
                        service,
                        department,
                        null
                ));
            } catch (NumberFormatException e) {
                System.out.println("Error: impossible to parse [" + values[5] + "] as a department number");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown service type [" + values[4] + "]");
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
    public void update(String[] values) throws ElementNotFound, FailedToUpdate
    {
        try {
            Service service = Service.valueOf(values[4]);
            try {
                int department = Integer.parseInt(values[5]);
                this.workerRepository.update(values[1], Worker.of(
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
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown service type [" + values[4] + "]");
        }
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.workerRepository.remove(id);
    }
}
