package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonDataAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WorkerRepository implements Repository<Worker>
{
    private final List<Worker> workers;
    private final JsonDataAccessor<Worker> jsonDataAccessor;

    public WorkerRepository(JsonDataAccessor<Worker> jsonDataAccessor)
    {
        this.jsonDataAccessor = jsonDataAccessor;
        this.workers = this.getDataFromJsonFile();
    }

    private List<Worker> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonDataAccessor.getDataFromFile()));
    }

    private void writeJsonFile()
    {
        this.jsonDataAccessor.writeInFile(this.workers);
    }

    @Override
    public void create(Worker worker) throws FailedToCreate
    {
        Worker registeredWorker = this.workers.stream()
                .filter(w -> w.getLogin().equals(worker.getLogin()))
                .findFirst()
                .orElse(null);

        if (registeredWorker != null)
        {
            throw new FailedToCreate(Worker.class);
        }

        this.workers.add(worker);
        this.writeJsonFile();
    }

    @Override
    public Stream<Worker> read()
    {
        return this.workers.stream();
    }

    @Override
    public Worker read(String id) throws ElementNotFound
    {
        Worker registeredWorker = this.workers.stream()
                .filter(w -> w.getId().toString().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredWorker == null)
        {
            throw new ElementNotFound(Worker.class, id);
        }

        return registeredWorker;
    }

    @Override
    public void update(Worker worker) throws ElementNotFound, FailedToUpdate
    {

    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        Worker registeredWorker = this.workers.stream()
                .filter(w -> w.getId().toString().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredWorker == null)
        {
            throw new ElementNotFound(Worker.class, id);
        }

        this.workers.remove(registeredWorker);
        this.writeJsonFile();
    }
}
