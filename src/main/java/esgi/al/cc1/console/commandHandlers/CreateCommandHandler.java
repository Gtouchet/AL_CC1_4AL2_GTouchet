package esgi.al.cc1.console.commandHandlers;

import esgi.al.cc1.console.enumerators.CommandKeyword;
import esgi.al.cc1.console.enumerators.TableName;
import esgi.al.cc1.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.controllers.Controller;
import esgi.al.cc1.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.models.Payment;
import esgi.al.cc1.models.Tradesman;

public class CreateCommandHandler
{
    private final Controller<Tradesman> userController;
    private final Controller<Payment> paymentController;

    public CreateCommandHandler(Controller<Tradesman> userController, Controller<Payment> paymentController)
    {
        this.userController = userController;
        this.paymentController = paymentController;
    }

    public void handle(String[] params)
    {
        if (params[1].equalsIgnoreCase(String.valueOf(TableName.USER)))
        {
            if (params.length != 9)
            {
                System.out.println(CommandKeyword.CREATE.usageExample);
                return;
            }

            try {
                int streetNumber = Integer.parseInt(params[6]);
                try {
                    this.userController.create(
                            Tradesman.of(params[2], params[3], params[4], params[5].trim().toLowerCase())
                    );
                } catch (InvalidModelParameter | FailedToCreate e2) {
                    System.out.println(e2.getMessage());
                }
            } catch (NumberFormatException e1) {
                System.out.println("Impossible to apply [" + params[6] + "] as a street number");
            }
        }

        else
        {
            if (params.length != 5)
            {
                System.out.println(CommandKeyword.CREATE.usageExample);
                return;
            }

            try {
                this.userController.read(params[2].toLowerCase());

                try {
                    float amount = Float.parseFloat(params[3]);
                    try {
                        this.paymentController.create(Payment.of(params[2].toLowerCase(), amount, params[4]));

                    } catch (InvalidModelParameter | FailedToCreate e) {
                        System.out.println(e.getMessage());
                    }
                } catch (NumberFormatException ignored) {
                    System.out.println("Impossible to apply [" + params[3] + "] as an amount");
                }
            } catch (ElementNotFound e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
