package esgi.al.cliInterpreter.cliCommandHandlers;

import esgi.al.cliInterpreter.Table;
import esgi.al.controllers.Controller;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.models.Payment;
import esgi.al.models.User;

public class DeleteCommandHandler
{
    private final Controller<User> userController;
    private final Controller<Payment> paymentController;

    private final String commandExample;

    public DeleteCommandHandler(Controller<User> userController, Controller<Payment> paymentController, String[] params)
    {
        this.userController = userController;
        this.paymentController = paymentController;

        this.commandExample = "Invalid syntax, DELETE user/payment id";

        this.handle(params);
    }

    private void handle(String[] params)
    {
        if (params[1].equalsIgnoreCase(String.valueOf(Table.USER)))
        {
            if (params.length != 3)
            {
                System.out.println(this.commandExample);
                return;
            }

            try {
                this.userController.del(params[2].toLowerCase());

            } catch (ElementNotFound e) {
                System.err.println(e.getMessage());
            }
        }

        else
        {

        }
    }
}
