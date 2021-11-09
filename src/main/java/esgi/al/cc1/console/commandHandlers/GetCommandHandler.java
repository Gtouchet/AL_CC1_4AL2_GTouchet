package esgi.al.console.commandHandlers;

import esgi.al.console.enumerators.CommandKeyword;
import esgi.al.console.enumerators.TableName;
import esgi.al.controllers.Controller;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.models.Payment;
import esgi.al.models.User;

public class GetCommandHandler
{
    private final Controller<User> userController;
    private final Controller<Payment> paymentController;

    public GetCommandHandler(Controller<User> userController, Controller<Payment> paymentController)
    {
        this.userController = userController;
        this.paymentController = paymentController;
    }

    public void handle(String[] params)
    {
        if (params[1].equalsIgnoreCase(String.valueOf(TableName.USER)))
        {
            if (params.length == 2)
            {
                this.userController.read().forEach(System.out::println);
            }
            else if (params.length == 3)
            {
                try {
                    System.out.println(this.userController.read(params[2].toLowerCase()));

                } catch (ElementNotFound e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                System.out.println(CommandKeyword.GET.usageExample);
            }
        }

        else
        {
            if (params.length == 2)
            {
                this.paymentController.read().forEach(System.out::println);
            }
            else if (params.length == 3)
            {
                try {
                    System.out.println(this.paymentController.read(params[2].toLowerCase()));

                } catch (ElementNotFound e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                System.out.println(CommandKeyword.GET.usageExample);
            }
        }
    }
}
