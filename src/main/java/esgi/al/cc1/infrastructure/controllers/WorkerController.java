package esgi.al.cc1.infrastructure.controllers;

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

    @Override
    public void create(Worker element) throws FailedToCreate
    {

    }

    @Override
    public Stream<Worker> read()
    {
        return null;
    }

    @Override
    public Worker read(String id) throws ElementNotFound
    {
        return null;
    }

    @Override
    public void update(Worker element) throws ElementNotFound, FailedToUpdate
    {

    }

    @Override
    public void remove(String id) throws ElementNotFound
    {

    }
}
