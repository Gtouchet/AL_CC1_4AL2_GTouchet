package esgi.al.cc1.infrastructure.factories;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.repositories.*;

public class RepositoriesFactory
{
    public Repository<Contractor> createContractorRepository()
    {
        return new ContractorRepository(new JsonAccessorsFactory().createContractorJsonAccessor());
    }

    public Repository<Payment> createPaymentRepository()
    {
        return new PaymentRepository(new JsonAccessorsFactory().createPaymentJsonAccessor());
    }

    public Repository<Project> createProjectRepository()
    {
        return new ProjectRepository(new JsonAccessorsFactory().createProjectJsonAccessor());
    }

    public Repository<Worker> createWorkerRepository()
    {
        return new WorkerRepository(new JsonAccessorsFactory().createWorkerJsonAccessor());
    }
}