package esgi.al.cc1.application.console;

import esgi.al.cc1.application.commandHandlers.paymentCommandHandlers.CreatePaymentHandler;
import esgi.al.cc1.application.commandHandlers.paymentCommandHandlers.DeletePaymentHandler;
import esgi.al.cc1.application.commandHandlers.paymentCommandHandlers.ReadPaymentHandler;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.factories.ControllersFactory;

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
                // Payment handlers
                case createPayment:
                    new CreatePaymentHandler(this.controllersFactory.createPaymentController()).handle(params);
                    break;

                case readPayment:
                    new ReadPaymentHandler(this.controllersFactory.createPaymentController()).handle(params);
                    break;

                case deletePayment:
                    new DeletePaymentHandler(this.controllersFactory.createPaymentController()).handle(params);
                    break;


                // Todo: implements other handlers


                // Unknown command
                default:
                    System.out.println("Unrecognized command [" + params[0].toUpperCase() + "]");
            }
        } catch (WrongNumberOfArgument e) {
            System.out.println(e.getMessage());
        }
    }
}
