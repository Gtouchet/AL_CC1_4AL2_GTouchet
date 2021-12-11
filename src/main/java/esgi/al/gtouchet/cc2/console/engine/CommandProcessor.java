package esgi.al.gtouchet.cc2.console.engine;

import esgi.al.gtouchet.cc2.application.ServicesFactory;
import esgi.al.gtouchet.cc2.console.commandHandlers.contractor.*;
import esgi.al.gtouchet.cc2.console.commandHandlers.miscellaneous.HelpHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.payment.CreatePaymentHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.payment.DeletePaymentHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.payment.ReadPaymentHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.project.*;
import esgi.al.gtouchet.cc2.console.commandHandlers.worker.CreateWorkerHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.worker.DeleteWorkerHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.worker.ReadWorkerHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.worker.UpdateWorkerHandler;

public class CommandProcessor
{
    private final ServicesFactory servicesFactory;

    public CommandProcessor(ServicesFactory servicesFactory)
    {
        this.servicesFactory = servicesFactory;
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
                case CREATE_CONTRACTOR: new CreateContractorHandler(this.servicesFactory.createContractorService()).handle(params); break;
                case READ_CONTRACTOR: new ReadContractorHandler(this.servicesFactory.createContractorService()).handle(params); break;
                case UPDATE_CONTRACTOR: new UpdateContractorHandler(this.servicesFactory.createContractorService()).handle(params); break;
                case DELETE_CONTRACTOR: new DeleteContractorHandler(this.servicesFactory.createContractorService()).handle(params); break;
                case VALIDATE_PAYMENT: new ValidatePaymentHandler(this.servicesFactory.createContractorService()).handle(params); break;

                // Payment handlers
                case CREATE_PAYMENT: new CreatePaymentHandler(this.servicesFactory.createPaymentService()).handle(params); break;
                case READ_PAYMENT: new ReadPaymentHandler(this.servicesFactory.createPaymentService()).handle(params); break;
                case DELETE_PAYMENT: new DeletePaymentHandler(this.servicesFactory.createPaymentService()).handle(params); break;

                // Project handlers
                case CREATE_PROJECT: new CreateProjectHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case READ_PROJECT: new ReadProjectHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case UPDATE_PROJECT: new UpdateProjectHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case DELETE_PROJECT: new DeleteProjectHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case ENGAGE_WORKER: new EngageWorkerHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case FIRE_WORKER: new FireWorkerHandler(this.servicesFactory.createProjectService()).handle(params); break;

                // Worker handlers
                case CREATE_WORKER: new CreateWorkerHandler(this.servicesFactory.createWorkerService()).handle(params); break;
                case READ_WORKER: new ReadWorkerHandler(this.servicesFactory.createWorkerService()).handle(params); break;
                case UPDATE_WORKER: new UpdateWorkerHandler(this.servicesFactory.createWorkerService()).handle(params); break;
                case DELETE_WORKER: new DeleteWorkerHandler(this.servicesFactory.createWorkerService()).handle(params); break;

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
