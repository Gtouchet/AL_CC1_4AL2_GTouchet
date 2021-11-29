package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.factories.RepositoriesFactory;
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
            throw new FailedToCreate(Worker.class, "login already in use by user ID [" + registeredWorker.getId().toString() + "]");
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
        return this.findById(id);
    }

    @Override
    public void update(String id, Worker worker) throws ElementNotFound, FailedToUpdate
    {
        Worker registeredWorker = this.findById(id);

        this.workers.remove(registeredWorker);

        registeredWorker.setPassword(worker.getPassword().toString());
        registeredWorker.setName(worker.getName());
        registeredWorker.setService(worker.getService());
        registeredWorker.setDepartment(worker.getDepartment());

        this.workers.add(registeredWorker);

        this.writeJsonFile();
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.workers.remove(this.findById(id));

        // Delete this worker for any projects they're working on
        // Todo: use factory to inject dependency !
        Repository<Project> pr = new RepositoriesFactory().createProjectRepository();
        pr.read().forEach(p -> {
            p.getWorkersId().forEach(workerId -> {
                if (workerId.toString().equals(id)) {
                    p.getWorkersId().remove(workerId);
                    // Todo: write projects json file after modification, how (private) ?
                }
            });
        });

        this.writeJsonFile();
    }

    @Override
    public void validatePayment(String id) throws ElementNotFound
    {
        // Do nothing
    }

    private Worker findById(String id) throws ElementNotFound
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
}
