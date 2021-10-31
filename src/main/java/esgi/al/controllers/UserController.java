package esgi.al.controllers;

import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.repositories.UsersRepository;

import java.util.stream.Stream;

public class UserController
{
    private final UsersRepository userRepository;

    public UserController(UsersRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void create(
            String login, String password, String name, String paymentMethod,
            String city, String streetType, String streetName, int streetNumber) throws FailedToCreateUser
    {
        this.userRepository.create(
                login, password, name, paymentMethod,
                city, streetType, streetName, streetNumber
        );
    }

    public Stream<User> getAll() throws NoUserFound
    {
        return this.userRepository.getAll();
    }

    public User getById(String id) throws NoUserFound
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

    public Stream<User> getByPaymentMethod(String paymentMethod) throws NoUserFound
    {
        return this.userRepository.getByPaymentMethod(paymentMethod);
    }

    public void updatePasswordBy(String isId, String idOrLogin, String newPassword) throws NoUserFound, FailedToUpdateUser
    {
        this.userRepository.updatePasswordBy(isId, idOrLogin, newPassword);
    }

    public void updateNameBy(String isId, String idOrLogin, String newName) throws NoUserFound, FailedToUpdateUser
    {
        this.userRepository.updateNameBy(isId, idOrLogin, newName);
    }

    public void deleteBy(String isId, String idOrLogin) throws NoUserFound, FailedToUpdateUser
    {
        this.userRepository.deleteBy(isId, idOrLogin);
    }

    public void register(User user)
    {
        this.userRepository.register(user);
    }
}
