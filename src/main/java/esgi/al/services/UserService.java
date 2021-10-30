package esgi.al.services;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.models.Users;

import java.util.UUID;
import java.util.stream.Stream;

public class UserService
{
    private final Users users;

    public UserService(Users users)
    {
        this.users = users;
    }

    public void createUser(User user) throws FailedToCreateUser
    {
       this.users.add(user);
    }

    public Stream<User> getAll() throws NoUserFound
    {
        return this.users.getAll();
    }

    public User getById(UUID id) throws NoUserFound
    {
        return this.users.getById(id);
    }

    public User getByLogin(String login) throws NoUserFound
    {
        return this.users.getByLogin(login);
    }

    public Stream<User> getByName(String name) throws NoUserFound
    {
        return this.users.getByName(name);
    }

    public Stream<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFound
    {
        return this.users.getByPaymentMethod(paymentMethod);
    }

    public void updatePassword(UUID id, String newPassword) throws NoUserFound, FailedToCreateUser
    {
        final User user = users.getById(id);
        user.updatePassword(newPassword);
        this.users.add(user);
    }
}
