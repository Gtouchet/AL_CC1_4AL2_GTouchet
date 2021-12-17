package esgi.al.gtouchet.cc2.console.engine;

import esgi.al.gtouchet.cc2.application.services.Services;
import esgi.al.gtouchet.cc2.application.services.contractor.*;
import esgi.al.gtouchet.cc2.application.services.payment.*;
import esgi.al.gtouchet.cc2.application.services.project.*;
import esgi.al.gtouchet.cc2.application.services.worker.*;
import esgi.al.gtouchet.cc2.console.commandHandlers.contractor.*;
import esgi.al.gtouchet.cc2.console.commandHandlers.miscellaneous.HelpHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.payment.*;
import esgi.al.gtouchet.cc2.console.commandHandlers.project.*;
import esgi.al.gtouchet.cc2.console.commandHandlers.worker.*;

public class CommandProcessor
{
    private final Services services;

    public CommandProcessor(Services services)
    {
        this.services = services;
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
                    new CreateContractorCommandHandler(this.services.retrieve(CreateContractorServiceHandler.class)).handle(params);
                    break;
                case READ_CONTRACTOR:
                    new ReadContractorCommandHandler(
                            this.services.retrieve(ReadAllContractorServiceHandler.class),
                            this.services.retrieve(ReadIdContractorServiceHandler.class)).handle(params);
                    break;
                case UPDATE_CONTRACTOR:
                    new UpdateContractorCommandHandler(this.services.retrieve(UpdateContractorServiceHandler.class)).handle(params);
                    break;
                case DELETE_CONTRACTOR:
                    new DeleteContractorCommandHandler(this.services.retrieve(DeleteContractorServiceHandler.class)).handle(params);
                    break;
                case VALIDATE_PAYMENT:
                    new ValidatePaymentCommandHandler(this.services.retrieve(ValidatePaymentServiceHandler.class)).handle(params);
                    break;

                // Payment handlers
                case CREATE_PAYMENT:
                    new CreatePaymentHandler(this.services.retrieve(CreatePaymentServiceHandler.class)).handle(params);
                    break;
                case READ_PAYMENT:
                    new ReadPaymentHandler(
                            this.services.retrieve(ReadAllPaymentServiceHandler.class),
                            this.services.retrieve(ReadIdPaymentServiceHandler.class)).handle(params);
                    break;
                case DELETE_PAYMENT:
                    new DeletePaymentHandler(this.services.retrieve(DeletePaymentServiceHandler.class)).handle(params);
                    break;

                // Project handlers
                case CREATE_PROJECT:
                    new CreateProjectHandler(this.services.retrieve(CreateProjectServiceHandler.class)).handle(params);
                    break;
                case READ_PROJECT:
                    new ReadProjectHandler(
                            this.services.retrieve(ReadAllProjectServiceHandler.class),
                            this.services.retrieve(ReadIdProjectServiceHandler.class)).handle(params);
                    break;
                case UPDATE_PROJECT:
                    new UpdateProjectHandler(this.services.retrieve(UpdateProjectServiceHandler.class)).handle(params);
                    break;
                case DELETE_PROJECT:
                    new DeleteProjectHandler(this.services.retrieve(DeleteProjectServiceHandler.class)).handle(params);
                    break;
                case ENGAGE_WORKER:
                    new EngageWorkerHandler(this.services.retrieve(EngageWorkerServiceHandler.class)).handle(params);
                        break;
                case FIRE_WORKER:
                    new FireWorkerHandler(this.services.retrieve(FireWorkerServiceHandler.class)).handle(params);
                    break;

                // Worker handlers
                case CREATE_WORKER:
                    new CreateWorkerHandler(this.services.retrieve(CreateWorkerServiceHandler.class)).handle(params);
                    break;
                case READ_WORKER:
                    new ReadWorkerHandler(
                            this.services.retrieve(ReadAllWorkerServiceHandler.class),
                            this.services.retrieve(ReadIdWorkerServiceHandler.class)).handle(params);
                    break;
                case UPDATE_WORKER:
                    new UpdateWorkerHandler(this.services.retrieve(UpdateWorkerServiceHandler.class)).handle(params);
                    break;
                case DELETE_WORKER:
                    new DeleteWorkerHandler(this.services.retrieve(DeleteWorkerServiceHandler.class)).handle(params);
                    break;

                // Help handler
                case HELP: new HelpHandler().handle(params); break;

                // Unknown command
                default: System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]");
            }
        } catch (WrongNumberOfArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
