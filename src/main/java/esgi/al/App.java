package esgi.al;

import esgi.al.controllers.UserController;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.models.User;
import esgi.al.repositories.UsersRepository;
import esgi.al.utils.JsonHelper;

import java.util.stream.Stream;

/**
 * Todo: SQL like command handler
 * The user can write a command per line in a text file
 * The command handler engine will read, line by line, the file
 * Then it will extract the command and parameters of the line
 * Ex. user creation : CREATE, GTouchet, 1234Abcd?, Guillaume, Paris, rue, Faubourg Saint Antoine, 242, PAYPAL
 * Ex. get all users : GETALL
 * Ex. get user by login : GETBYLOGIN, GTouchet
 * Ex: user deletion by ID : DELETEBYID, 123abcd-ef456[...]
 */
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
                userController.createUser(User.of(jsonUser));
            } catch (FailedToCreateUser e) {
                e.printStackTrace();
            }
        });
    }
}