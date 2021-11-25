package esgi.al.cc1.repositories;

import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.models.ServiceProvider;

import java.util.stream.Stream;

public class ServiceProviderRepository implements Repository<ServiceProvider>
{
    @Override
    public void create(ServiceProvider element) throws FailedToCreate {

    }

    @Override
    public Stream<ServiceProvider> read() {
        return null;
    }

    @Override
    public ServiceProvider read(String id) throws ElementNotFound {
        return null;
    }

    @Override
    public void update(ServiceProvider element) throws ElementNotFound, FailedToCreate {

    }

    @Override
    public void remove(String id) throws ElementNotFound {

    }
}
