package esgi.al.console.commandHandlers;

import esgi.al.console.enumerators.CommandKeyword;
import esgi.al.console.enumerators.TableName;
import esgi.al.controllers.Controller;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.models.Payment;
import esgi.al.models.User;

public class DeleteCommandHandler
{
    private final Controller<User> userController;
    private final Controller<Payment> paymentController;

    public DeleteCommandHandler(Controller<User> userController, Controller<Payment> paymentController)
    {
        this.userController = userController;
        this.paymentController = paymentController;
    }

    public void handle(String[] params)
    {
        if (params[1].equalsIgnoreCase(String.valueOf(TableName.USER)))
        {
            if (params.length != 3)
            {
                System.out.println(CommandKeyword.DELETE.usageExample);
                return;
            }

            try {
                this.userController.remove(params[2].toLowerCase());

            } catch (ElementNotFound e) {
                System.out.println(e.getMessage());
            }
        }

        else
        {
            if (params.length != 3)
            {
                System.out.println(CommandKeyword.DELETE.usageExample);
                return;
            }

            try {
                this.paymentController.remove(params[2].toLowerCase());

            } catch (ElementNotFound e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
