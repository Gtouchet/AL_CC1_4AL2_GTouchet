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

        if (params[0].equals("") || params[0].equalsIgnoreCase(Command.quit.keyword))
        {
            return;
        }

        try {
            switch (Command.getCommand(params[0].toUpperCase()))
            {
                // Contractor handlers
                case createContractor: new CreateContractorHandler(this.servicesFactory.createContractorService()).handle(params); break;
                case readContractor: new ReadContractorHandler(this.servicesFactory.createContractorService()).handle(params); break;
                case updateContractor: new UpdateContractorHandler(this.servicesFactory.createContractorService()).handle(params); break;
                case deleteContractor: new DeleteContractorHandler(this.servicesFactory.createContractorService()).handle(params); break;
                case validatePayment: new ValidatePaymentHandler(this.servicesFactory.createContractorService()).handle(params); break;

                // Payment handlers
                case createPayment: new CreatePaymentHandler(this.servicesFactory.createPaymentService()).handle(params); break;
                case readPayment: new ReadPaymentHandler(this.servicesFactory.createPaymentService()).handle(params); break;
                case deletePayment: new DeletePaymentHandler(this.servicesFactory.createPaymentService()).handle(params); break;

                // Project handlers
                case createProject: new CreateProjectHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case readProject: new ReadProjectHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case updateProject: new UpdateProjectHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case deleteProject: new DeleteProjectHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case engageWorker: new EngageWorkerHandler(this.servicesFactory.createProjectService()).handle(params); break;
                case fireWorker: new FireWorkerHandler(this.servicesFactory.createProjectService()).handle(params); break;

                // Worker handlers
                case createWorker: new CreateWorkerHandler(this.servicesFactory.createWorkerService()).handle(params); break;
                case readWorker: new ReadWorkerHandler(this.servicesFactory.createWorkerService()).handle(params); break;
                case updateWorker: new UpdateWorkerHandler(this.servicesFactory.createWorkerService()).handle(params); break;
                case deleteWorker: new DeleteWorkerHandler(this.servicesFactory.createWorkerService()).handle(params); break;

                // Help handler
                case help: new HelpHandler().handle(params); break;

                // Unknown command
                default: System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]");
            }
        } catch (WrongNumberOfArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
