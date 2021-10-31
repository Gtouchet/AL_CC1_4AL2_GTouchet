package esgi.al;

import esgi.al.controllers.UserController;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.models.User;
import esgi.al.repositories.UsersRepository;
import esgi.al.utils.CommandInputHandler;
import esgi.al.utils.JsonHelper;

import java.util.stream.Stream;

public class App
{
    public static void main(String[] args)
    {
        final UsersRepository userRepository = new UsersRepository();
        final UserController userController = new UserController(userRepository);

        // Initialize repository by fetching json file's data
        Stream<User> jsonUsers = JsonHelper.getUserDataFromFile();
        jsonUsers.forEach(jsonUser -> {
            try {
                userController.registerUser(User.of(jsonUser));
            } catch (FailedToCreateUser e) {
                e.printStackTrace();
            }
        });

        new CommandInputHandler(userController).launch();
    }
}