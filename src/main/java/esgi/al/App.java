package esgi.al;

import esgi.al.controllers.UserController;
import esgi.al.daos.UserDao;
import esgi.al.repositories.UsersRepository;
import esgi.al.utils.JsonHelper;

import java.util.stream.Stream;

public class App
{
    public static void main(String[] args)
    {
        final UsersRepository userRepository = new UsersRepository();
        final UserController userController = new UserController(userRepository);

        // Initialize repository's data by fetching json file's data
        final Stream<UserDao> jsonUsers = JsonHelper.getUserDataFromFile();
        jsonUsers.forEach(jsonUser ->
            userController.register(new UserDao(
                    jsonUser.id,
                    jsonUser.login,
                    jsonUser.password,
                    jsonUser.name,
                    jsonUser.paymentMethod,
                    jsonUser.address
            ))
        );

        new CliProcessingEngine(userController).launch();
    }
}