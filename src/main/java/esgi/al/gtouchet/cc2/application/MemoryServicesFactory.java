package esgi.al.gtouchet.cc2.application;

import esgi.al.gtouchet.cc2.application.contractorServices.ContractorServicesFactory;
import esgi.al.gtouchet.cc2.application.paymentServices.PaymentServicesFactory;
import esgi.al.gtouchet.cc2.application.projectServices.ProjectServicesFactory;
import esgi.al.gtouchet.cc2.application.workerServices.WorkerServicesFactory;
import esgi.al.gtouchet.cc2.infrastructure.repositories.RepositoriesFactory;

public class MemoryServicesFactory implements ServicesFactory
{
    private final RepositoriesFactory repositoriesFactory;

    public MemoryServicesFactory(RepositoriesFactory repositoriesFactory)
    {
        this.repositoriesFactory = repositoriesFactory;
    }

    @Override
    public ContractorServicesFactory createContractorServicesFactory()
    {
        return new ContractorServicesFactory(this.repositoriesFactory);
    }

    @Override
    public PaymentServicesFactory createPaymentServicesFactory()
    {
        return new PaymentServicesFactory(this.repositoriesFactory);
    }

    @Override
    public ProjectServicesFactory createProjectServicesFactory()
    {
        return new ProjectServicesFactory(this.repositoriesFactory);
    }

    @Override
    public WorkerServicesFactory createWorkerServicesFactory()
    {
        return new WorkerServicesFactory(this.repositoriesFactory);
    }
}
