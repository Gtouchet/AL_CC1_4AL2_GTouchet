package esgi.al.factories;

import esgi.al.controllers.Controller;
import esgi.al.controllers.UserController;
import esgi.al.models.User;

public class ControllersFactory
{
    public Controller<User> createUserController()
    {
        return new UserController(new RepositoriesFactory().createUserRepository());
    }
}
