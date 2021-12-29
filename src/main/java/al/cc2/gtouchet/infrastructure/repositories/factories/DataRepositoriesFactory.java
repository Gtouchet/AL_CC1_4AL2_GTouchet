package al.cc2.gtouchet.infrastructure.repositories.factories;

import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.payment.Payment;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.infrastructure.dataAccessors.JsonDataAccessor;
import al.cc2.gtouchet.infrastructure.repositories.DataRepository;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class DataRepositoriesFactory implements RepositoriesFactory
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