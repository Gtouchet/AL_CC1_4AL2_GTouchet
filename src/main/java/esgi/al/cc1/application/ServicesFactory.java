package esgi.al.cc1.application;

import esgi.al.cc1.domain.validators.PasswordValidator;
import esgi.al.cc1.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.cc1.infrastructure.repositories.RepositoriesFactory;

public class ServicesFactory
{
    public ContractorService createContractorService()
    {
        return new ContractorServiceImpl(
                new RepositoriesFactory().createContractorRepository(),
                new RepositoriesFactory().createWorkerRepository(),
                new PaymentMethodValidatorApi(),
                new PasswordValidator()
        );
    }

    public PaymentService createPaymentService()
    {
        return new PaymentServiceImpl(
                new RepositoriesFactory().createPaymentRepository(),
                new RepositoriesFactory().createContractorRepository(),
                new RepositoriesFactory().createWorkerRepository()
        );
    }

    public ProjectService createProjectService()
    {
        return new ProjectServiceImpl(
                new RepositoriesFactory().createProjectRepository(),
                new RepositoriesFactory().createContractorRepository(),
                new RepositoriesFactory().createWorkerRepository()
        );
    }

    public WorkerService createWorkerService()
    {
        return new WorkerServiceImpl(
                new RepositoriesFactory().createWorkerRepository(),
                new RepositoriesFactory().createContractorRepository(),
                new RepositoriesFactory().createProjectRepository(),
                new PasswordValidator()
        );
    }
}
