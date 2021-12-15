package esgi.al.gtouchet.cc2.application;

import esgi.al.gtouchet.cc2.application.contractorServices.ContractorServicesFactory;
import esgi.al.gtouchet.cc2.application.paymentServices.PaymentServicesFactory;
import esgi.al.gtouchet.cc2.application.projectServices.ProjectServicesFactory;
import esgi.al.gtouchet.cc2.application.workerServices.WorkerServicesFactory;
import esgi.al.gtouchet.cc2.infrastructure.repositories.DataRepositoriesFactory;

public class ServicesFactory
{
    private final DataRepositoriesFactory dataRepositoriesFactory;

    public ServicesFactory(DataRepositoriesFactory dataRepositoriesFactory)
    {
        this.dataRepositoriesFactory = dataRepositoriesFactory;
    }

    public ContractorServicesFactory createContractorServicesFactory()
    {
        return new ContractorServicesFactory(this.dataRepositoriesFactory);
    }

    public PaymentServicesFactory createPaymentServicesFactory()
    {
        return new PaymentServicesFactory(this.dataRepositoriesFactory);
    }

    public ProjectServicesFactory createProjectServicesFactory()
    {
        return new ProjectServicesFactory(this.dataRepositoriesFactory);
    }

    public WorkerServicesFactory createWorkerServicesFactory()
    {
        return new WorkerServicesFactory(this.dataRepositoriesFactory);
    }
}
