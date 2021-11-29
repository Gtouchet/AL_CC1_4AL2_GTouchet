package esgi.al.cc1.infrastructure.factories;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.controllers.*;

public class ControllersFactory
{
    public Controller<Contractor> createContractorController()
    {
        return new ContractorController(new RepositoriesFactory().createContractorRepository());
    }

    public Controller<Payment> createPaymentController()
    {
        return new PaymentController(
                new RepositoriesFactory().createPaymentRepository(),
                new RepositoriesFactory().createContractorRepository()
        );
    }

    public Controller<Project> createProjectController()
    {
        return new ProjectController(new RepositoriesFactory().createProjectRepository());
    }

    public Controller<Worker> createWorkerHandler()
    {
        return new WorkerController(new RepositoriesFactory().createWorkerRepository());
    }
}
