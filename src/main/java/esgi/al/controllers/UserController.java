package esgi.al.controllers;

import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.services.UserService;
import java.util.UUID;

public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    public void createUser(User user) throws FailedToCreateUser
    {
        this.userService.createUser(user);
    }

    public User getById(UUID id) throws NoUserFound
    {
        return this.userService.getById(id);
    }

    public void updatePassword(UUID id, String password) throws NoUserFound, FailedToCreateUser
    {
        this.userService.updatePassword(id, password);
    }
}
