package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.utilitaries.JsonAccessorsFactory;

public class RepositoriesFactory
{
    public Repository<Contractor> createContractorRepository()
    {
        return new RepositoryImpl<>(new JsonAccessorsFactory().createContractorJsonAccessor());
    }

    public Repository<Payment> createPaymentRepository()
    {
        return new RepositoryImpl<>(new JsonAccessorsFactory().createPaymentJsonAccessor());
    }

    public Repository<Project> createProjectRepository()
    {
        return new RepositoryImpl<>(new JsonAccessorsFactory().createProjectJsonAccessor());
    }

    public Repository<Worker> createWorkerRepository()
    {
        return new RepositoryImpl<>(new JsonAccessorsFactory().createWorkerJsonAccessor());
    }
}