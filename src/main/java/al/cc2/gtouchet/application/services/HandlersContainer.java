package al.cc2.gtouchet.application.services;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.handlers.contractor.*;
import al.cc2.gtouchet.application.services.handlers.payment.CreatePaymentCommandHandler;
import al.cc2.gtouchet.application.services.handlers.payment.DeletePaymentByIdCommandHandler;
import al.cc2.gtouchet.application.services.handlers.payment.ReadAllPaymentQueryHandler;
import al.cc2.gtouchet.application.services.handlers.payment.ReadPaymentByIdQueryHandler;
import al.cc2.gtouchet.application.services.handlers.project.*;
import al.cc2.gtouchet.application.services.handlers.worker.*;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.payment.Payment;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.Repository;
import al.cc2.gtouchet.infrastructure.repositories.factories.RepositoriesFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class HandlersContainer
{
    private final List<CommandHandler> commandHandlers;
    private final List<QueryHandler> queryHandlers;

    private HandlersContainer()
    {
        this.commandHandlers = new ArrayList<>();
        this.queryHandlers = new ArrayList<>();
    }

    public void register(CommandHandler commandHandler) throws NullPointerException
    {
        Objects.requireNonNull(commandHandler);
        if (!this.commandHandlers.contains(commandHandler))
        {
            this.commandHandlers.add(commandHandler);
        }
    }

    public void register(QueryHandler queryHandler) throws NullPointerException
    {
        Objects.requireNonNull(queryHandler);
        if (!this.queryHandlers.contains(queryHandler))
        {
            this.queryHandlers.add(queryHandler);
        }
    }

    public CommandHandler getCommandHandler(Class command)
    {
        return this.commandHandlers.stream()
                .filter(registeredCommand -> registeredCommand.getClass().equals(command))
                .findFirst()
                .orElse(null);
    }

    public QueryHandler getQueryHandler(Class query)
    {
        return this.queryHandlers.stream()
                .filter(registeredQuery -> registeredQuery.getClass().equals(query))
                .findFirst()
                .orElse(null);
    }

    public static HandlersContainer initialize(
            RepositoriesFactory repositoriesFactory,
            PasswordValidator passwordValidator,
            PaymentMethodValidatorApi paymentMethodValidatorApi)
    {
        HandlersContainer handlersContainer = new HandlersContainer();

        Repository<Contractor> contractorRepository = repositoriesFactory.createContractorRepository();
        Repository<Payment> paymentRepository = repositoriesFactory.createPaymentRepository();
        Repository<Project> projectRepository = repositoriesFactory.createProjectRepository();
        Repository<Worker> workerRepository = repositoriesFactory.createWorkerRepository();

        try {
            // Register Contractor services
            handlersContainer.register(new CreateContractorCommandHandler(contractorRepository, workerRepository, passwordValidator));
            handlersContainer.register(new DeleteContractorByIdCommandHandler(contractorRepository));
            handlersContainer.register(new ReadAllContractorQueryHandler(contractorRepository));
            handlersContainer.register(new ReadContractorByIdQueryHandler(contractorRepository));
            handlersContainer.register(new UpdateContractorByIdCommandHandler(contractorRepository, passwordValidator));
            handlersContainer.register(new ValidatePaymentByIdCommandHandler(contractorRepository, paymentMethodValidatorApi));
            handlersContainer.register(new ReadContractorByPaymentQueryHandler(contractorRepository));

            // Register Payment services
            handlersContainer.register(new CreatePaymentCommandHandler(paymentRepository, contractorRepository, workerRepository));
            handlersContainer.register(new DeletePaymentByIdCommandHandler(paymentRepository));
            handlersContainer.register(new ReadAllPaymentQueryHandler(paymentRepository));
            handlersContainer.register(new ReadPaymentByIdQueryHandler(paymentRepository));

            // Register Project services
            handlersContainer.register(new CreateProjectCommandHandler(projectRepository, contractorRepository));
            handlersContainer.register(new DeleteProjectByIdCommandHandler(projectRepository));
            handlersContainer.register(new EngageWorkerCommandHandler(projectRepository, workerRepository));
            handlersContainer.register(new FireWorkerCommandHandler(projectRepository, workerRepository));
            handlersContainer.register(new ReadAllProjectQueryHandler(projectRepository));
            handlersContainer.register(new ReadProjectByIdQueryHandler(projectRepository));
            handlersContainer.register(new UpdateProjectByIdCommandHandler(projectRepository, contractorRepository));

            // Register Worker services
            handlersContainer.register(new CreateWorkerCommandHandler(workerRepository, contractorRepository, passwordValidator));
            handlersContainer.register(new DeleteWorkerByIdCommandHandler(workerRepository, projectRepository));
            handlersContainer.register(new ReadAllWorkerQueryHandler(workerRepository));
            handlersContainer.register(new ReadWorkerByIdQueryHandler(workerRepository));
            handlersContainer.register(new UpdateWorkerByIdCommandHandler(workerRepository, passwordValidator));

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return handlersContainer;
    }
}
