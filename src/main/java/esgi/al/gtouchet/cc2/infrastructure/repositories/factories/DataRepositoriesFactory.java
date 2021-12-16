package esgi.al.gtouchet.cc2.infrastructure.repositories.factories;

import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.infrastructure.dataAccessors.JsonDataAccessor;
import esgi.al.gtouchet.cc2.infrastructure.repositories.DataRepository;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

public class DataRepositoriesFactory implements RepositoriesFactory
{
    @Override
    public Repository<Contractor> createContractorRepository()
    {
        return new DataRepository<>(new JsonDataAccessor<>(Contractor.class));
    }

    @Override
    public Repository<Payment> createPaymentRepository()
    {
        return new DataRepository<>(new JsonDataAccessor<>(Payment.class));
    }

    @Override
    public Repository<Project> createProjectRepository()
    {
        return new DataRepository<>(new JsonDataAccessor<>(Project.class));
    }

    @Override
    public Repository<Worker> createWorkerRepository()
    {
        return new DataRepository<>(new JsonDataAccessor<>(Worker.class));
    }
}