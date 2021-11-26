package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WorkerRepository implements Repository<Worker>
{
    private final List<Worker> workers;
    private final JsonAccessor<Worker> jsonAccessor;

    public WorkerRepository(JsonAccessor<Worker> jsonAccessor)
    {
        this.jsonAccessor = jsonAccessor;
        this.workers = this.getDataFromJsonFile();
    }

    private List<Worker> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonAccessor.getDataFromFile()));
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
