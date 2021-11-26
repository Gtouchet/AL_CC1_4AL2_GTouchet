package esgi.al.cc1.console.commandHandlers;

import esgi.al.cc1.console.enumerators.CommandKeyword;
import esgi.al.cc1.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.controllers.Controller;
import esgi.al.cc1.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.models.Tradesman;

public class UpdateCommandHandler
{
    private final Controller<Tradesman> userController;

    public UpdateCommandHandler(Controller<Tradesman> userController)
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
                        Tradesman.of(params[2].toLowerCase(), params[3], params[4], params[5], params[6].trim().toLowerCase())
                );
            } catch ( InvalidModelParameter | ElementNotFound | FailedToCreate e2) {
                System.out.println(e2.getMessage());
            }
        } catch (NumberFormatException e1) {
            System.out.println("Impossible to apply [" + params[7] + "] as a street number");
        }
    }
}
