package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.utilitaries.JsonDataAccessor;

public class RepositoriesFactory
{
    public Repository<Contractor> createContractorRepository()
    {
        return new RepositoryImpl<>(new JsonDataAccessor<>(Contractor.class));
    }

    public Repository<Payment> createPaymentRepository()
    {
        return new RepositoryImpl<>(new JsonDataAccessor<>(Payment.class));
    }

    public Repository<Project> createProjectRepository()
    {
        return new RepositoryImpl<>(new JsonDataAccessor<>(Project.class));
    }

    public Repository<Worker> createWorkerRepository()
    {
        return new RepositoryImpl<>(new JsonDataAccessor<>(Worker.class));
    }
}