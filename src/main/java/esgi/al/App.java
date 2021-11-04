package esgi.al;

import esgi.al.controllers.Controller;
import esgi.al.factories.ControllersFactory;
import esgi.al.models.User;

public class App
{
    public static void main(String[] args)
    {
        final Controller<User> userController = new ControllersFactory().createUserController();
        new CliProcessingEngine(userController).launch();
    }
}
