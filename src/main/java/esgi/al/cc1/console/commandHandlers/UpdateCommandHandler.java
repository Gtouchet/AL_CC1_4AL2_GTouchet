package esgi.al.console.commandHandlers;

import esgi.al.console.enumerators.CommandKeyword;
import esgi.al.controllers.Controller;
import esgi.al.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.models.Address;
import esgi.al.models.User;

public class UpdateCommandHandler
{
    private final Controller<User> userController;

    public UpdateCommandHandler(Controller<User> userController)
    {
        this.userController = userController;
    }

    public void handle(String[] params)
    {
        if (params.length != 10)
        {
            System.out.println(CommandKeyword.UPDATE.usageExample);
            return;
        }

        try {
            int streetNumber = Integer.parseInt(params[7]);
            try {
                this.userController.update(
                        User.of(params[2].toLowerCase(), params[3], params[4], params[5], params[6].trim().toLowerCase(),
                        Address.of(streetNumber, params[8], params[9]))
                );
            } catch ( InvalidModelParameter | ElementNotFound | FailedToCreate e2) {
                System.out.println(e2.getMessage());
            }
        } catch (NumberFormatException e1) {
            System.out.println("Impossible to apply [" + params[7] + "] as a street number");
        }
    }
}
