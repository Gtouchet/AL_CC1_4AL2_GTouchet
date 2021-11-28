package esgi.al.cc1.application.engine;

import esgi.al.cc1.application.commandHandlers.HelpHandler;
import esgi.al.cc1.application.commandHandlers.payment.CreatePaymentHandler;
import esgi.al.cc1.application.commandHandlers.payment.DeletePaymentHandler;
import esgi.al.cc1.application.commandHandlers.payment.ReadPaymentHandler;
import esgi.al.cc1.application.commandHandlers.worker.CreateWorkerHandler;
import esgi.al.cc1.application.commandHandlers.worker.DeleteWorkerHandler;
import esgi.al.cc1.application.commandHandlers.worker.ReadWorkerHandler;
import esgi.al.cc1.application.commandHandlers.worker.UpdateWorkerHandler;
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
            // Todo: implements other handlers
            switch (Command.getCommand(params[0].toUpperCase()))
            {
                // Contractor handlers
                case createContractor: break;
                case readContractor: break;
                case updateContractor: break;
                case deleteContractor: break;

                // Payment handlers
                case createPayment: new CreatePaymentHandler(this.controllersFactory.createPaymentController()).handle(params); break;
                case readPayment: new ReadPaymentHandler(this.controllersFactory.createPaymentController()).handle(params); break;
                case deletePayment: new DeletePaymentHandler(this.controllersFactory.createPaymentController()).handle(params); break;

                // Project handlers
                case createProject: break;
                case readProject: break;
                case updateProject: break;
                case deleteProject: break;

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
