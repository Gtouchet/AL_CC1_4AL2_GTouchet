package esgi.al;

import esgi.al.controllers.UserController;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.models.User;
import esgi.al.models.Users;
import esgi.al.repositories.SQLUsers;
import esgi.al.services.UserService;
import esgi.al.utils.JsonReader;
import java.util.List;

public class App
{
    public static void main(String[] args) throws FailedToCreateUser
    {
        final Users users = new SQLUsers();
        final UserService userService = new UserService(users);
        final UserController userController = new UserController(userService);

        List<User> jsonUsers = JsonReader.getUserDataFrom("./res/registeredUsers.json");
        /*for (User jsonUser : jsonUsers)
        {
            System.out.println(jsonUser);
            userController.createUser(jsonUser);
        }*/
        //System.out.println(userController.getAll());
    }
}
