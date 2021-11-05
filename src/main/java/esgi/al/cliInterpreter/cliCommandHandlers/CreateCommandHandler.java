package esgi.al.cliInterpreter.cliCommandHandlers;

import esgi.al.cliInterpreter.Table;
import esgi.al.controllers.Controller;
import esgi.al.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.models.Address;
import esgi.al.models.Payment;
import esgi.al.models.User;

public class CreateCommandHandler
{
    private final Controller<User> userController;
    private final Controller<Payment> paymentController;

    private final String userCommandExample;
    private final String paymentCommandExample;

    public CreateCommandHandler(Controller<User> userController, Controller<Payment> paymentController, String[] params)
    {
        this.userController = userController;
        this.paymentController = paymentController;

        this.userCommandExample = "Invalid syntax, CREATE USER login password name paymentMethod number street city";
        this.paymentCommandExample = "Invalid syntax, CREATE PAYMENT userId amount reason";

        this.handle(params);
    }

    private void handle(String[] params)
    {
        if (params[1].equalsIgnoreCase(String.valueOf(Table.USER)))
        {
            if (params.length != 9)
            {
                System.out.println(this.userCommandExample);
                return;
            }

            try {
                int streetNumber = Integer.parseInt(params[6]);
                try {
                    this.userController.post(
                            User.of(params[2], params[3], params[4], params[5].trim().toLowerCase(),
                            Address.of(streetNumber, params[7], params[8]))
                    );
                } catch (InvalidModelParameter | FailedToCreate e2) {
                    System.err.println(e2.getMessage());
                }
            } catch (NumberFormatException e1) {
                System.err.println("Impossible to apply [" + params[6] + "] as a street number");
            }
        }

        else
        {
            if (params.length != 5)
            {
                System.out.println(this.paymentCommandExample);
                return;
            }

            try {
                this.userController.get(params[2].toLowerCase());

                try {
                    float amount = Float.parseFloat(params[3]);
                    try {
                        this.paymentController.post(Payment.of(params[2].toLowerCase(), amount, params[4]));

                    } catch (InvalidModelParameter | FailedToCreate e) {
                        System.err.println(e.getMessage());
                    }
                } catch (NumberFormatException ignored) {
                    System.err.println("Impossible to apply [" + params[3] + "] as an amount");
                }
            } catch (ElementNotFound e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
