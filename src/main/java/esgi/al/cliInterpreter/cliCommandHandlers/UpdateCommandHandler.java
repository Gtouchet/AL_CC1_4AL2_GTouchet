package esgi.al.cliInterpreter.cliCommandHandlers;

import esgi.al.cliInterpreter.Table;
import esgi.al.controllers.Controller;
import esgi.al.exceptions.modelsExceptions.InvalidAddressParameter;
import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.models.Address;
import esgi.al.models.Payment;
import esgi.al.models.User;

public class UpdateCommandHandler
{
    private final Controller<User> userController;
    private final Controller<Payment> paymentController;

    private final String commandExample;

    public UpdateCommandHandler(Controller<User> userController, Controller<Payment> paymentController, String[] params)
    {
        this.userController = userController;
        this.paymentController = paymentController;

        this.commandExample = "Invalid syntax, UPDATE user/payment id login password name paymentMethod number street city";

        this.handle(params);
    }

    private void handle(String[] params)
    {
        if (params[1].equalsIgnoreCase(String.valueOf(Table.USER)))
        {
            if (params.length != 10)
            {
                System.out.println(this.commandExample);
                return;
            }

            try {
                int streetNumber = Integer.parseInt(params[7]);
                try {
                    this.userController.put(
                            User.of(params[2].toLowerCase(), params[3], params[4], params[5], params[6].trim().toLowerCase(),
                            Address.of(streetNumber, params[8], params[9]))
                    );
                } catch (InvalidUserParameter | InvalidAddressParameter | ElementNotFound | FailedToCreate e2) {
                    System.err.println(e2.getMessage());
                }
            } catch (NumberFormatException e1) {
                System.err.println("Impossible to apply [" + params[7] + "] as a street number");
            }
        }

        else
        {

        }
    }
}
