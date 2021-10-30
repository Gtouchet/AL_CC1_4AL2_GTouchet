package esgi.al.controllers;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.repositories.UsersRepository;

import java.util.UUID;
import java.util.stream.Stream;

public class UserController
{
    private final UsersRepository userRepository;

    public UserController(UsersRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void createUser(User user) throws FailedToCreateUser
    {
        this.userRepository.create(user);
    }

    public Stream<User> getAll() throws NoUserFound
    {
        return this.userRepository.getAll();
    }

    public User getById(UUID id) throws NoUserFound
    {
        return this.userRepository.getById(id);
    }

    public User getByLogin(String login) throws NoUserFound
    {
        return this.userRepository.getByLogin(login);
    }

    public Stream<User> getByName(String name) throws NoUserFound
    {
        return this.userRepository.getByName(name);
    }

    public Stream<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFound
    {
        return this.userRepository.getByPaymentMethod(paymentMethod);
    }

    public void deleteById(UUID id) throws NoUserFound
    {
        this.userRepository.deleteById(id);
    }
}
