package esgi.al.gtouchet.cc2.console.engine;

import esgi.al.gtouchet.cc2.application.services.factories.ServicesFactory;
import esgi.al.gtouchet.cc2.application.services.factories.ContractorServicesFactory;
import esgi.al.gtouchet.cc2.application.services.factories.PaymentServicesFactory;
import esgi.al.gtouchet.cc2.application.services.factories.ProjectServicesFactory;
import esgi.al.gtouchet.cc2.application.services.factories.WorkerServicesFactory;
import esgi.al.gtouchet.cc2.console.commandHandlers.contractor.*;
import esgi.al.gtouchet.cc2.console.commandHandlers.miscellaneous.HelpHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.payment.*;
import esgi.al.gtouchet.cc2.console.commandHandlers.project.*;
import esgi.al.gtouchet.cc2.console.commandHandlers.worker.*;

public class CommandProcessor
{
    private final ContractorServicesFactory contractorServicesFactory;
    private final PaymentServicesFactory paymentServicesFactory;
    private final ProjectServicesFactory projectServicesFactory;
    private final WorkerServicesFactory workerServicesFactory;

    public CommandProcessor(ServicesFactory servicesFactory)
    {
        this.contractorServicesFactory = servicesFactory.createContractorServicesFactory();
        this.paymentServicesFactory = servicesFactory.createPaymentServicesFactory();
        this.projectServicesFactory = servicesFactory.createProjectServicesFactory();
        this.workerServicesFactory = servicesFactory.createWorkerServicesFactory();
    }

    public void process(String command)
    {
        String[] params = command.trim().split(" ");

        if (params[0].equals("") || params[0].equalsIgnoreCase(Command.QUIT.keyword))
        {
            return;
        }

        try {
            switch (Command.getCommand(params[0].toUpperCase()))
            {
                // Contractor handlers
                case CREATE_CONTRACTOR:
                    new CreateContractorCommandHandler(this.contractorServicesFactory.getCreateContractorHandler()).handle(params);
                    break;
                case READ_CONTRACTOR:
                    new ReadContractorCommandHandler(
                            this.contractorServicesFactory.getReadAllContractorHandler(),
                            this.contractorServicesFactory.getReadIdContractorHandler()).handle(params);
                    break;
                case UPDATE_CONTRACTOR:
                    new UpdateContractorCommandHandler(this.contractorServicesFactory.getUpdateContractorHandler()).handle(params);
                    break;
                case DELETE_CONTRACTOR:
                    new DeleteContractorCommandHandler(this.contractorServicesFactory.getDeleteContractorService()).handle(params);
                    break;
                case VALIDATE_PAYMENT:
                    new ValidatePaymentCommandHandler(this.contractorServicesFactory.getValidatePaymentService()).handle(params);
                    break;

                // Payment handlers
                case CREATE_PAYMENT:
                    new CreatePaymentHandler(this.paymentServicesFactory.getCreatePaymentHandler()).handle(params);
                    break;
                case READ_PAYMENT:
                    new ReadPaymentHandler(
                            this.paymentServicesFactory.getReadAllPaymentHandler(),
                            this.paymentServicesFactory.getReadIdPaymentHandler()).handle(params);
                    break;
                case DELETE_PAYMENT:
                    new DeletePaymentHandler(this.paymentServicesFactory.getDeletePaymentHandler()).handle(params);
                    break;

                // Project handlers
                case CREATE_PROJECT:
                    new CreateProjectHandler(this.projectServicesFactory.getCreateProjectHandler()).handle(params);
                    break;
                case READ_PROJECT:
                    new ReadProjectHandler(
                            this.projectServicesFactory.getReadAllProjectHandler(),
                            this.projectServicesFactory.getReadIdProjectHandle()).handle(params);
                    break;
                case UPDATE_PROJECT:
                    new UpdateProjectHandler(this.projectServicesFactory.getUpdateProjectHandler()).handle(params);
                    break;
                case DELETE_PROJECT:
                    new DeleteProjectHandler(this.projectServicesFactory.getDeleteProjectHandler()).handle(params);
                    break;
                case ENGAGE_WORKER:
                    new EngageWorkerHandler(this.projectServicesFactory.getEngageProjectHandler()).handle(params);
                        break;
                case FIRE_WORKER:
                    new FireWorkerHandler(this.projectServicesFactory.getFireProjectHandler()).handle(params);
                    break;

                // Worker handlers
                case CREATE_WORKER:
                    new CreateWorkerHandler(this.workerServicesFactory.getCreateWorkerHandler()).handle(params);
                    break;
                case READ_WORKER:
                    new ReadWorkerHandler(
                            this.workerServicesFactory.getReadAllWorkerHandler(),
                            this.workerServicesFactory.getReadIdWorkerHandler()).handle(params);
                    break;
                case UPDATE_WORKER:
                    new UpdateWorkerHandler(this.workerServicesFactory.getUpdateWorkerHandler()).handle(params);
                    break;
                case DELETE_WORKER:
                    new DeleteWorkerHandler(this.workerServicesFactory.getDeleteWorkerHandler()).handle(params);
                    break;

                // Help handler
                case HELP: new HelpHandler().handle(params); break;

                // Unknown command
                default: System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]");
            }
        } catch (WrongNumberOfArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
