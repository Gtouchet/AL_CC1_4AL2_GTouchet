package al.cc2.gtouchet.application.services;

import al.cc2.gtouchet.application.services.contractor.*;
import al.cc2.gtouchet.application.services.payment.*;
import al.cc2.gtouchet.application.services.project.*;
import al.cc2.gtouchet.application.services.worker.*;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.Repository;
import al.cc2.gtouchet.infrastructure.repositories.factories.RepositoriesFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicesContainer
{
    private final List<ServiceHandler> services;

    private ServicesContainer()
    {
        this.services = new ArrayList<>();
    }

    public void register(ServiceHandler service) throws NullPointerException
    {
        Objects.requireNonNull(service);
        if (!this.services.contains(service))
        {
            this.services.add(service);
        }
    }

    public ServiceHandler retrieve(Class/*<ServiceHandler>*/ service)
    {
        return this.services.stream()
                .filter(registeredService -> registeredService.getClass().equals(service))
                .findFirst()
                .orElse(null);
    }

    public static ServicesContainer initialize(
            RepositoriesFactory repositoriesFactory,
            PasswordValidator passwordValidator,
            PaymentMethodValidatorApi paymentMethodValidatorApi)
    {
        ServicesContainer servicesContainer = new ServicesContainer();

        Repository<Contractor> contractorRepository = repositoriesFactory.createContractorRepository();
        Repository<Payment> paymentRepository = repositoriesFactory.createPaymentRepository();
        Repository<Project> projectRepository = repositoriesFactory.createProjectRepository();
        Repository<Worker> workerRepository = repositoriesFactory.createWorkerRepository();

        try {
            // Register Contractor services
            servicesContainer.register(new CreateContractorServiceHandler(contractorRepository, workerRepository, passwordValidator));
            servicesContainer.register(new DeleteContractorServiceHandler(contractorRepository));
            servicesContainer.register(new ReadAllContractorServiceHandler(contractorRepository));
            servicesContainer.register(new ReadIdContractorServiceHandler(contractorRepository));
            servicesContainer.register(new UpdateContractorServiceHandler(contractorRepository, passwordValidator));
            servicesContainer.register(new ValidatePaymentServiceHandler(contractorRepository, paymentMethodValidatorApi));

            // Register Payment services
            servicesContainer.register(new CreatePaymentServiceHandler(paymentRepository, contractorRepository, workerRepository));
            servicesContainer.register(new DeletePaymentServiceHandler(paymentRepository));
            servicesContainer.register(new ReadAllPaymentServiceHandler(paymentRepository));
            servicesContainer.register(new ReadIdPaymentServiceHandler(paymentRepository));

            // Register Project services
            servicesContainer.register(new CreateProjectServiceHandler(projectRepository, contractorRepository));
            servicesContainer.register(new DeleteProjectServiceHandler(projectRepository));
            servicesContainer.register(new EngageWorkerServiceHandler(projectRepository, workerRepository));
            servicesContainer.register(new FireWorkerServiceHandler(projectRepository, workerRepository));
            servicesContainer.register(new ReadAllProjectServiceHandler(projectRepository));
            servicesContainer.register(new ReadIdProjectServiceHandler(projectRepository));
            servicesContainer.register(new UpdateProjectServiceHandler(projectRepository, contractorRepository));

            // Register Worker services
            servicesContainer.register(new CreateWorkerServiceHandler(workerRepository, contractorRepository, passwordValidator));
            servicesContainer.register(new DeleteWorkerServiceHandler(workerRepository, projectRepository));
            servicesContainer.register(new ReadAllWorkerServiceHandler(workerRepository));
            servicesContainer.register(new ReadIdWorkerServiceHandler(workerRepository));
            servicesContainer.register(new UpdateWorkerServiceHandler(workerRepository, passwordValidator));

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return servicesContainer;
    }
}
