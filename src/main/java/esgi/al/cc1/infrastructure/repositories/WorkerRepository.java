package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.utilitaries.JsonDataAccessor;

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

    @Override
    public void create(Worker worker) throws FailedToCreateException
    {
        Worker registeredWorker = this.workers.stream()
                .filter(w -> w.getLogin().equals(worker.getLogin()))
                .findFirst()
                .orElse(null);

        if (registeredWorker != null)
        {
            throw new FailedToCreateException(Worker.class, "login already in use by user ID [" + registeredWorker.getId() + "]");
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
    public Worker read(Id id) throws ElementNotFoundException
    {
        return this.findById(id);
    }

    @Override
    public void update(Id id, Worker worker) throws ElementNotFoundException, FailedToUpdateException
    {
        Worker registeredWorker = this.findById(id);

        this.workers.remove(registeredWorker);

        registeredWorker.setPassword(worker.getPassword());
        registeredWorker.setName(worker.getName());
        registeredWorker.setService(worker.getService());
        registeredWorker.setDepartment(worker.getDepartment());

        this.workers.add(registeredWorker);
        this.writeJsonFile();
    }

    @Override
    public void remove(Id id) throws ElementNotFoundException
    {
        this.workers.remove(this.findById(id));
        this.writeJsonFile();
    }

    @Override
    public boolean exists(Id id)
    {
        return this.workers.stream().anyMatch(worker -> worker.getId().equals(id));
    }

    private List<Worker> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonDataAccessor.getDataFromFile()));
    }

    private void writeJsonFile()
    {
        this.jsonDataAccessor.writeInFile(this.workers);
    }

    private Worker findById(Id id) throws ElementNotFoundException
    {
        Worker registeredWorker = this.workers.stream()
                .filter(worker -> worker.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredWorker == null)
        {
            throw new ElementNotFoundException(Worker.class, id.toString());
        }

        return registeredWorker;
    }
}
