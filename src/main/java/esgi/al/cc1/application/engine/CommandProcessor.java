package esgi.al.cc1.application.engine;

import esgi.al.cc1.application.commandHandlers.HelpHandler;
import esgi.al.cc1.application.commandHandlers.contractor.*;
import esgi.al.cc1.application.commandHandlers.payment.*;
import esgi.al.cc1.application.commandHandlers.project.*;
import esgi.al.cc1.application.commandHandlers.worker.*;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.infrastructure.factories.ControllersFactory;

public class CommandProcessor
{
    private final ControllersFactory controllersFactory;

    public CommandProcessor(ControllersFactory controllersFactory)
    {
        this.controllersFactory = controllersFactory;
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
                case createContractor: new CreateContractorHandler(this.controllersFactory.createContractorController()).handle(params); break;
                case readContractor: new ReadContractorHandler(this.controllersFactory.createContractorController()).handle(params); break;
                case updateContractor: new UpdateContractorHandler(this.controllersFactory.createContractorController()).handle(params); break;
                case deleteContractor: new DeleteContractorHandler(this.controllersFactory.createContractorController()).handle(params); break;
                case validatePayment: new ValidatePaymentHandler(this.controllersFactory.createContractorController()).handle(params); break;

                // Payment handlers
                case createPayment: new CreatePaymentHandler(this.controllersFactory.createPaymentController()).handle(params); break;
                case readPayment: new ReadPaymentHandler(this.controllersFactory.createPaymentController()).handle(params); break;
                case deletePayment: new DeletePaymentHandler(this.controllersFactory.createPaymentController()).handle(params); break;

                // Project handlers
                case createProject: new CreateProjectHandler(this.controllersFactory.createProjectController()).handle(params); break;
                case readProject: break;
                case updateProject: break;
                case deleteProject: break;
                case engageWorker: break; // todo, also handlers implementations
                case fireWorker: break;

                // Worker handlers
                case createWorker: new CreateWorkerHandler(this.controllersFactory.createWorkerHandler()).handle(params); break;
                case readWorker: new ReadWorkerHandler(this.controllersFactory.createWorkerHandler()).handle(params); break;
                case updateWorker: new UpdateWorkerHandler(this.controllersFactory.createWorkerHandler()).handle(params); break;
                case deleteWorker: new DeleteWorkerHandler(this.controllersFactory.createWorkerHandler()).handle(params); break;

                // Help command
                case help: new HelpHandler().handle(params); break;

                // Unknown command
                default: System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]");
            }
        } catch (WrongNumberOfArgument e) {
            System.out.println(e.getMessage());
        }
    }
}
