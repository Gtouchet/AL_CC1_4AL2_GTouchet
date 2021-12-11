package esgi.al.gtouchet.cc2.infrastructure.repositories;

import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.infrastructure.dataAccessors.JsonDataAccessor;

public class RepositoriesFactory
{
    public Repository<Contractor> createContractorRepository()
    {
        return new DataRepository<>(new JsonDataAccessor<>(Contractor.class));
    }

    public Repository<Payment> createPaymentRepository()
    {
        return new DataRepository<>(new JsonDataAccessor<>(Payment.class));
    }

    public Repository<Project> createProjectRepository()
    {
        return new DataRepository<>(new JsonDataAccessor<>(Project.class));
    }

    public Repository<Worker> createWorkerRepository()
    {
        return new DataRepository<>(new JsonDataAccessor<>(Worker.class));
    }
}