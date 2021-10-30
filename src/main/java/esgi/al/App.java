package esgi.al;

import esgi.al.controllers.UserController;
import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.repositories.UsersRepository;
import esgi.al.utils.JsonHelper;

import java.util.UUID;
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

        try {
            // Testing
            System.out.println("\n---------- All users ----------");
            userController.getAll().forEach(System.out::println);

            System.out.println("\n---------- By ID ----------");
            System.out.println(userController.getById(UUID.fromString("ffa3734b-b34c-43cd-b986-95cff858a81b")));

            System.out.println("\n---------- By login ----------");
            System.out.println(userController.getByLogin("GTouchet2"));

            System.out.println("\n---------- By name ----------");
            userController.getByName("Touchet").forEach(System.out::println);

            System.out.println("\n---------- By payment method ----------");
            userController.getByPaymentMethod(PaymentMethod.CARD).forEach(System.out::println);

            System.out.println("\n---------- Deletion ----------");
            //userController.deleteById(UUID.fromString(""));

            System.out.println("\n---------- Update password ----------");
            userController.updatePasswordBy(false, "GTouchet", "789_Test_:)");

            System.out.println("\n---------- Update name ----------");
            userController.updateNameBy(false, "GTouchet", "Guy");

        } catch (NoUserFound | FailedToUpdateUser e) {
            e.printStackTrace();
        }
    }
}