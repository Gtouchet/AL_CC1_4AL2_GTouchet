package esgi.al.controllers;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.services.UserService;

import java.util.UUID;
import java.util.stream.Stream;

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

    public Stream<User> getAll() throws NoUserFound
    {
        return this.userService.getAll();
    }

    public User getById(UUID id) throws NoUserFound
    {
        return this.userService.getById(id);
    }

    public User getByLogin(String login) throws NoUserFound
    {
        return this.userService.getByLogin(login);
    }

    public Stream<User> getByName(String name) throws NoUserFound
    {
        return this.userService.getByName(name);
    }

    public Stream<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFound
    {
        return this.userService.getByPaymentMethod(paymentMethod);
    }

    public void updatePassword(UUID id, String password) throws NoUserFound, FailedToCreateUser
    {
        this.userService.updatePassword(id, password);
    }
}
