package al.cc2.gtouchet.infrastructure.repositories.factories;

import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.infrastructure.dataAccessors.JsonDataAccessor;
import al.cc2.gtouchet.infrastructure.repositories.DataRepository;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

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