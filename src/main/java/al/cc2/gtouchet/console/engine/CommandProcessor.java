package al.cc2.gtouchet.console.engine;

import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.application.services.handlers.contractor.*;
import al.cc2.gtouchet.application.services.handlers.payment.CreatePaymentCommandHandler;
import al.cc2.gtouchet.application.services.handlers.payment.DeletePaymentCommandHandler;
import al.cc2.gtouchet.application.services.handlers.payment.ReadAllPaymentQueryHandler;
import al.cc2.gtouchet.application.services.handlers.payment.ReadPaymentQueryHandler;
import al.cc2.gtouchet.application.services.handlers.project.*;
import al.cc2.gtouchet.application.services.handlers.worker.*;
import al.cc2.gtouchet.console.handlers.contractor.*;
import al.cc2.gtouchet.console.handlers.miscellaneous.HelpConsoleHandler;
import al.cc2.gtouchet.console.handlers.payment.CreatePaymentConsoleHandler;
import al.cc2.gtouchet.console.handlers.payment.DeletePaymentConsoleHandler;
import al.cc2.gtouchet.console.handlers.payment.ReadPaymentConsoleHandler;
import al.cc2.gtouchet.console.handlers.project.*;
import al.cc2.gtouchet.console.handlers.worker.CreateWorkerConsoleHandler;
import al.cc2.gtouchet.console.handlers.worker.DeleteWorkerConsoleHandler;
import al.cc2.gtouchet.console.handlers.worker.ReadWorkerConsoleHandler;
import al.cc2.gtouchet.console.handlers.worker.UpdateWorkerConsoleHandler;

public class CommandProcessor
{
    private final HandlersContainer handlersContainer;

    public CommandProcessor(HandlersContainer handlersContainer)
    {
        this.handlersContainer = handlersContainer;
    }

    public void process(String command)
    {
        String[] params = command.trim().split(" ");

        if (params[0].equals("") || params[0].equalsIgnoreCase(ConsoleCommand.QUIT.keyword))
        {
            return;
        }

        try {
            switch (ConsoleCommand.getCommand(params[0].toUpperCase()))
            {
                /*
                 * Contractor console handlers
                 */
                case CREATE_CONTRACTOR:
                    new CreateContractorConsoleHandler(this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class)).handle(params);
                    break;
                case READ_CONTRACTOR:
                    new ReadContractorConsoleHandler(
                            this.handlersContainer.getQueryHandler(ReadAllContractorQueryHandler.class),
                            this.handlersContainer.getQueryHandler(ReadContractorQueryHandler.class)).handle(params);
                    break;
                case UPDATE_CONTRACTOR:
                    new UpdateContractorConsoleHandler(this.handlersContainer.getCommandHandler(UpdateContractorCommandHandler.class)).handle(params);
                    break;
                case DELETE_CONTRACTOR:
                    new DeleteContractorConsoleHandler(this.handlersContainer.getCommandHandler(DeleteContractorCommandHandler.class)).handle(params);
                    break;
                case VALIDATE_PAYMENT:
                    new ValidatePaymentConsoleHandler(this.handlersContainer.getCommandHandler(ValidatePaymentCommandHandler.class)).handle(params);
                    break;

                /*
                 * Payment console handlers
                 */
                case CREATE_PAYMENT:
                    new CreatePaymentConsoleHandler(this.handlersContainer.getCommandHandler(CreatePaymentCommandHandler.class)).handle(params);
                    break;
                case READ_PAYMENT:
                    new ReadPaymentConsoleHandler(
                            this.handlersContainer.getQueryHandler(ReadAllPaymentQueryHandler.class),
                            this.handlersContainer.getQueryHandler(ReadPaymentQueryHandler.class)).handle(params);
                    break;
                case DELETE_PAYMENT:
                    new DeletePaymentConsoleHandler(this.handlersContainer.getCommandHandler(DeletePaymentCommandHandler.class)).handle(params);
                    break;

                /*
                 * Project console handlers
                 */
                case CREATE_PROJECT:
                    new CreateProjectConsoleHandler(this.handlersContainer.getCommandHandler(CreateProjectCommandHandler.class)).handle(params);
                    break;
                case READ_PROJECT:
                    new ReadProjectConsoleHandler(
                            this.handlersContainer.getQueryHandler(ReadAllProjectQueryHandler.class),
                            this.handlersContainer.getQueryHandler(ReadProjectQueryHandler.class)).handle(params);
                    break;
                case UPDATE_PROJECT:
                    new UpdateProjectConsoleHandler(this.handlersContainer.getCommandHandler(UpdateProjectCommandHandler.class)).handle(params);
                    break;
                case DELETE_PROJECT:
                    new DeleteProjectConsoleHandler(this.handlersContainer.getCommandHandler(DeleteProjectCommandHandler.class)).handle(params);
                    break;
                case ENGAGE_WORKER:
                    new EngageWorkerConsoleHandler(this.handlersContainer.getCommandHandler(EngageWorkerCommandHandler.class)).handle(params);
                        break;
                case FIRE_WORKER:
                    new FireWorkerConsoleHandler(this.handlersContainer.getCommandHandler(FireWorkerCommandHandler.class)).handle(params);
                    break;

                /*
                 * Worker console handlers
                 */
                case CREATE_WORKER:
                    new CreateWorkerConsoleHandler(this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class)).handle(params);
                    break;
                case READ_WORKER:
                    new ReadWorkerConsoleHandler(
                            this.handlersContainer.getQueryHandler(ReadAllWorkerQueryHandler.class),
                            this.handlersContainer.getQueryHandler(ReadWorkerQueryHandler.class)).handle(params);
                    break;
                case UPDATE_WORKER:
                    new UpdateWorkerConsoleHandler(this.handlersContainer.getCommandHandler(UpdateWorkerCommandHandler.class)).handle(params);
                    break;
                case DELETE_WORKER:
                    new DeleteWorkerConsoleHandler(this.handlersContainer.getCommandHandler(DeleteWorkerCommandHandler.class)).handle(params);
                    break;

                // Help console handler
                case HELP: new HelpConsoleHandler().handle(params); break;

                // Unknown console command
                default: System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]");
            }
        } catch (WrongNumberOfArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
