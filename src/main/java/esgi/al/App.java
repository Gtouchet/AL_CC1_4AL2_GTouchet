package esgi.al;

import esgi.al.controllers.UserController;
import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.repositories.UsersRepository;
import esgi.al.utils.JsonHelper;

import java.util.UUID;
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
            userController.deleteById(UUID.fromString("bfa3734b-b34c-43cd-b986-95cff858a81b"));

        } catch (NoUserFound e) {
            e.printStackTrace();
        }
    }
}