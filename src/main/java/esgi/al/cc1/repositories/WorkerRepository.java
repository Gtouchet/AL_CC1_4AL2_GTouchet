package esgi.al.cc1.repositories;

import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.models.Worker;

import java.util.stream.Stream;

public class WorkerRepository implements Repository<Worker>
{
    @Override
    public void create(Worker element) throws FailedToCreate {

    }

    @Override
    public Stream<Worker> read() {
        return null;
    }

    @Override
    public Worker read(String id) throws ElementNotFound {
        return null;
    }

    @Override
    public void update(Worker element) throws ElementNotFound, FailedToCreate {

    }

    @Override
    public void remove(String id) throws ElementNotFound {

    }
}
