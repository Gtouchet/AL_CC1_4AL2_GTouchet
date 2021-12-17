package esgi.al.gtouchet.cc2.application.services;

import esgi.al.gtouchet.cc2.application.services.contractor.*;
import esgi.al.gtouchet.cc2.application.services.payment.*;
import esgi.al.gtouchet.cc2.application.services.project.*;
import esgi.al.gtouchet.cc2.application.services.worker.*;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;

public abstract class ServicesInitializer
{
    public static Services initialize(RepositoriesFactory repositoriesFactory)
    {
        Repository<Contractor> contractorRepository = repositoriesFactory.createContractorRepository();
        Repository<Payment> paymentRepository = repositoriesFactory.createPaymentRepository();
        Repository<Project> projectRepository = repositoriesFactory.createProjectRepository();
        Repository<Worker> workerRepository = repositoriesFactory.createWorkerRepository();
        PasswordValidator passwordValidator = new PasswordValidator();
        PaymentMethodValidatorApi paymentMethodValidatorApi = new PaymentMethodValidatorApi();

        Services services = new Services();

        // Register Contractor services
        services.register(new CreateContractorServiceHandler(contractorRepository, workerRepository, passwordValidator));
        services.register(new DeleteContractorServiceHandler(contractorRepository));
        services.register(new ReadAllContractorServiceHandler(contractorRepository));
        services.register(new ReadIdContractorServiceHandler(contractorRepository));
        services.register(new UpdateContractorServiceHandler(contractorRepository, passwordValidator));
        services.register(new ValidatePaymentServiceHandler(contractorRepository, paymentMethodValidatorApi));

        // Register Payment services
        services.register(new CreatePaymentServiceHandler(paymentRepository, contractorRepository, workerRepository));
        services.register(new DeletePaymentServiceHandler(paymentRepository));
        services.register(new ReadAllPaymentServiceHandler(paymentRepository));
        services.register(new ReadIdPaymentServiceHandler(paymentRepository));

        // Register Project services
        services.register(new CreateProjectServiceHandler(projectRepository, contractorRepository));
        services.register(new DeleteProjectServiceHandler(projectRepository));
        services.register(new EngageWorkerServiceHandler(projectRepository, workerRepository));
        services.register(new FireWorkerServiceHandler(projectRepository, workerRepository));
        services.register(new ReadAllProjectServiceHandler(projectRepository));
        services.register(new ReadIdProjectServiceHandler(projectRepository));
        services.register(new UpdateProjectServiceHandler(projectRepository, contractorRepository));

        // Register Worker services
        services.register(new CreateWorkerServiceHandler(workerRepository, contractorRepository, passwordValidator));
        services.register(new DeleteWorkerServiceHandler(workerRepository, projectRepository));
        services.register(new ReadAllWorkerServiceHandler(workerRepository));
        services.register(new ReadIdWorkerServiceHandler(workerRepository));
        services.register(new UpdateWorkerServiceHandler(workerRepository, passwordValidator));

        return services;
    }
}
