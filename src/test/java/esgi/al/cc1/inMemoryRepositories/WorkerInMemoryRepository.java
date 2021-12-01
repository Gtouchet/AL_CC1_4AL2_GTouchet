package esgi.al.cc1.inMemoryRepositories;

import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WorkerInMemoryRepository implements Repository<Worker>
{
    private final List<Worker> workers;

    public WorkerInMemoryRepository()
    {
        this.workers = new ArrayList<>();
    }

    @Override
    public void create(Worker worker)
    {
        Worker registeredWorker = this.workers.stream()
                .filter(w -> w.getLogin().equals(worker.getLogin()))
                .findFirst()
                .orElse(null);

        if (registeredWorker != null)
        {
            return;
        }

        this.workers.add(worker);
    }

    @Override
    public Stream<Worker> read()
    {
        return this.workers.stream();
    }

    @Override
    public Worker read(Id id)
    {
        return this.findById(id);
    }

    @Override
    public void update(Id id, Worker worker)
    {
        Worker registeredWorker = this.findById(id);

        if (registeredWorker == null)
        {
            return;
        }

        this.workers.remove(registeredWorker);

        registeredWorker.setPassword(worker.getPassword());
        registeredWorker.setName(worker.getName());
        registeredWorker.setService(worker.getService());
        registeredWorker.setDepartment(worker.getDepartment());

        this.workers.add(registeredWorker);
    }

    @Override
    public void remove(Id id)
    {
        this.workers.remove(this.findById(id));
    }

    @Override
    public boolean exists(Id id)
    {
        return this.workers.stream().anyMatch(worker -> worker.getId().equals(id));
    }

    private Worker findById(Id id)
    {
        return this.workers.stream()
                .filter(worker -> worker.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
