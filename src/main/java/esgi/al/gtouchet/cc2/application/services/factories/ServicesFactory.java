package esgi.al.gtouchet.cc2.application.services.factories;

import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;

public class ServicesFactory
{
    private final RepositoriesFactory repositoriesFactory;

    public ServicesFactory(RepositoriesFactory repositoriesFactory)
    {
        this.repositoriesFactory = repositoriesFactory;
    }

    public ContractorServicesFactory createContractorServicesFactory()
    {
        return new ContractorServicesFactory(this.repositoriesFactory);
    }

    public PaymentServicesFactory createPaymentServicesFactory()
    {
        return new PaymentServicesFactory(this.repositoriesFactory);
    }

    public ProjectServicesFactory createProjectServicesFactory()
    {
        return new ProjectServicesFactory(this.repositoriesFactory);
    }

    public WorkerServicesFactory createWorkerServicesFactory()
    {
        return new WorkerServicesFactory(this.repositoriesFactory);
    }
}
