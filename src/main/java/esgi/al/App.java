package esgi.al;

import esgi.al.controllers.UserController;
import esgi.al.enumerators.PaymentMethod;
import esgi.al.enumerators.StreetType;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.Address;
import esgi.al.models.User;
import esgi.al.models.Users;
import esgi.al.repositories.SQLUsers;
import esgi.al.services.UserService;
import esgi.al.utils.Uuid;

public class App
{
    public static void main(String[] args) throws FailedToCreateUser, NoUserFound
    {
        final Users users = new SQLUsers();
        final UserService userService = new UserService(users);
        final UserController userController = new UserController(userService);

        User user = User.of(
                Uuid.generate(),
                "GTouchet",
                "Abcd1234!",
                "Touchet",
                Address.of(
                        "Draveil",
                        StreetType.rue,
                        "Pierre Brossolette",
                        134
                ),
                PaymentMethod.CARD
        );

        // We create the user, using the user controller
        userController.createUser(user);

        // We get the newly created user by it's ID
        System.out.println(userController.getById(user.getId()));

        // And here we DON'T find the newly user, throwing a NoUserFound exception
        // Unless we are very very VERY lucky !
        System.out.println(userController.getById(Uuid.generate()));
    }
}
