package esgi.al;

import esgi.al.controllers.Controller;
import esgi.al.controllers.UserController;
import esgi.al.models.User;
import esgi.al.repositories.Repository;
import esgi.al.repositories.UsersRepository;

public class App
{
    public static void main(String[] args)
    {
        // Todo: factoriiiiies
        final Repository<User> userRepository = new UsersRepository("./res/registeredUsers.json");
        final Controller<User> userController = new UserController(userRepository);

        new CliProcessingEngine(userController).launch();
    }
}