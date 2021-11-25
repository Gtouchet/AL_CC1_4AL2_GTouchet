package esgi.al.cc1.console.commandHandlers;

import esgi.al.cc1.console.enumerators.CommandKeyword;
import esgi.al.cc1.console.enumerators.TableName;
import esgi.al.cc1.controllers.Controller;
import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.models.Payment;
import esgi.al.cc1.models.Tradesman;

public class DeleteCommandHandler
{
    private final Controller<Tradesman> userController;
    private final Controller<Payment> paymentController;

    public DeleteCommandHandler(Controller<Tradesman> userController, Controller<Payment> paymentController)
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
