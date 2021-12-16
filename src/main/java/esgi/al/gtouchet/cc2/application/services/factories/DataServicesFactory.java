package esgi.al.gtouchet.cc2.application.services.factories;

import esgi.al.gtouchet.cc2.application.services.contractor.ContractorServicesFactory;
import esgi.al.gtouchet.cc2.application.services.payment.PaymentServicesFactory;
import esgi.al.gtouchet.cc2.application.services.project.ProjectServicesFactory;
import esgi.al.gtouchet.cc2.application.services.worker.WorkerServicesFactory;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;

public class DataServicesFactory implements ServicesFactory
{
    private final RepositoriesFactory repositoriesFactory;

    public DataServicesFactory(RepositoriesFactory repositoriesFactory)
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
