package esgi.al.cliInterpreter.cliCommandHandlers;

import esgi.al.cliInterpreter.Table;
import esgi.al.controllers.Controller;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.models.Payment;
import esgi.al.models.User;

public class GetCommandHandler
{
    private final Controller<User> userController;
    private final Controller<Payment> paymentController;

    private final String commandExample;

    public GetCommandHandler(Controller<User> userController, Controller<Payment> paymentController, String[] params)
    {
        this.userController = userController;
        this.paymentController = paymentController;

        this.commandExample = "Invalid syntax, GET USER/PAYMENT\nGET USER/PAYMENT id";

        this.handle(params);
    }

    private void handle(String[] params)
    {
        if (params[1].equalsIgnoreCase(String.valueOf(Table.USER)))
        {
            if (params.length == 2)
            {
                this.userController.get().forEach(System.out::println);
            }
            else if (params.length == 3)
            {
                try {
                    System.out.println(this.userController.get(params[2].toLowerCase()));

                } catch (ElementNotFound e) {
                    System.err.println(e.getMessage());
                }
            }
            else
            {
                System.out.println(this.commandExample);
            }
        }

        else
        {
            if (params.length == 2)
            {
                this.paymentController.get().forEach(System.out::println);
            }
            else if (params.length == 3)
            {
                try {
                    System.out.println(this.paymentController.get(params[2].toLowerCase()));

                } catch (ElementNotFound e) {
                    System.err.println(e.getMessage());
                }
            }
            else
            {
                System.out.println(this.commandExample);
            }
        }
    }
}
