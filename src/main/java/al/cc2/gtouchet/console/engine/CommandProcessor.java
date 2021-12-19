package al.cc2.gtouchet.console.engine;

import al.cc2.gtouchet.application.services.ServicesContainer;
import al.cc2.gtouchet.application.services.contractor.*;
import al.cc2.gtouchet.application.services.payment.*;
import al.cc2.gtouchet.application.services.project.*;
import al.cc2.gtouchet.application.services.worker.*;
import al.cc2.gtouchet.console.commandHandlers.contractor.*;
import al.cc2.gtouchet.console.commandHandlers.miscellaneous.HelpHandler;
import al.cc2.gtouchet.console.commandHandlers.payment.CreatePaymentHandler;
import al.cc2.gtouchet.console.commandHandlers.payment.DeletePaymentHandler;
import al.cc2.gtouchet.console.commandHandlers.payment.ReadPaymentHandler;
import al.cc2.gtouchet.console.commandHandlers.project.*;
import al.cc2.gtouchet.console.commandHandlers.worker.CreateWorkerHandler;
import al.cc2.gtouchet.console.commandHandlers.worker.DeleteWorkerHandler;
import al.cc2.gtouchet.console.commandHandlers.worker.ReadWorkerHandler;
import al.cc2.gtouchet.console.commandHandlers.worker.UpdateWorkerHandler;

public class CommandProcessor
{
    private final ServicesContainer servicesContainer;

    public CommandProcessor(ServicesContainer servicesContainer)
    {
        this.servicesContainer = servicesContainer;
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
                    new CreateContractorCommandHandler(this.servicesContainer.retrieve(CreateContractorServiceHandler.class)).handle(params);
                    break;
                case READ_CONTRACTOR:
                    new ReadContractorCommandHandler(
                            this.servicesContainer.retrieve(ReadAllContractorServiceHandler.class),
                            this.servicesContainer.retrieve(ReadIdContractorServiceHandler.class)).handle(params);
                    break;
                case UPDATE_CONTRACTOR:
                    new UpdateContractorCommandHandler(this.servicesContainer.retrieve(UpdateContractorServiceHandler.class)).handle(params);
                    break;
                case DELETE_CONTRACTOR:
                    new DeleteContractorCommandHandler(this.servicesContainer.retrieve(DeleteContractorServiceHandler.class)).handle(params);
                    break;
                case VALIDATE_PAYMENT:
                    new ValidatePaymentCommandHandler(this.servicesContainer.retrieve(ValidatePaymentServiceHandler.class)).handle(params);
                    break;

                // Payment handlers
                case CREATE_PAYMENT:
                    new CreatePaymentHandler(this.servicesContainer.retrieve(CreatePaymentServiceHandler.class)).handle(params);
                    break;
                case READ_PAYMENT:
                    new ReadPaymentHandler(
                            this.servicesContainer.retrieve(ReadAllPaymentServiceHandler.class),
                            this.servicesContainer.retrieve(ReadIdPaymentServiceHandler.class)).handle(params);
                    break;
                case DELETE_PAYMENT:
                    new DeletePaymentHandler(this.servicesContainer.retrieve(DeletePaymentServiceHandler.class)).handle(params);
                    break;

                // Project handlers
                case CREATE_PROJECT:
                    new CreateProjectHandler(this.servicesContainer.retrieve(CreateProjectServiceHandler.class)).handle(params);
                    break;
                case READ_PROJECT:
                    new ReadProjectHandler(
                            this.servicesContainer.retrieve(ReadAllProjectServiceHandler.class),
                            this.servicesContainer.retrieve(ReadIdProjectServiceHandler.class)).handle(params);
                    break;
                case UPDATE_PROJECT:
                    new UpdateProjectHandler(this.servicesContainer.retrieve(UpdateProjectServiceHandler.class)).handle(params);
                    break;
                case DELETE_PROJECT:
                    new DeleteProjectHandler(this.servicesContainer.retrieve(DeleteProjectServiceHandler.class)).handle(params);
                    break;
                case ENGAGE_WORKER:
                    new EngageWorkerHandler(this.servicesContainer.retrieve(EngageWorkerServiceHandler.class)).handle(params);
                        break;
                case FIRE_WORKER:
                    new FireWorkerHandler(this.servicesContainer.retrieve(FireWorkerServiceHandler.class)).handle(params);
                    break;

                // Worker handlers
                case CREATE_WORKER:
                    new CreateWorkerHandler(this.servicesContainer.retrieve(CreateWorkerServiceHandler.class)).handle(params);
                    break;
                case READ_WORKER:
                    new ReadWorkerHandler(
                            this.servicesContainer.retrieve(ReadAllWorkerServiceHandler.class),
                            this.servicesContainer.retrieve(ReadIdWorkerServiceHandler.class)).handle(params);
                    break;
                case UPDATE_WORKER:
                    new UpdateWorkerHandler(this.servicesContainer.retrieve(UpdateWorkerServiceHandler.class)).handle(params);
                    break;
                case DELETE_WORKER:
                    new DeleteWorkerHandler(this.servicesContainer.retrieve(DeleteWorkerServiceHandler.class)).handle(params);
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
