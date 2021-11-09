package esgi.al.controllers;

import esgi.al.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.models.User;
import esgi.al.repositories.Repository;
import esgi.al.validators.AddressValidator;
import esgi.al.validators.UserValidator;

import java.util.stream.Stream;

public class UserController implements Controller<User>
{
    private final Repository<User> userRepository;

    public UserController(Repository<User> userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) throws InvalidModelParameter, FailedToCreate
    {
        UserValidator.validate(user);
        AddressValidator.validate(user.getAddress());

        this.userRepository.create(user);
    }

    @Override
    public Stream<User> read()
    {
        return this.userRepository.read();
    }

    @Override
    public User read(String id) throws ElementNotFound
    {
        return this.userRepository.read(id);
    }

    @Override
    public void update(User user) throws InvalidModelParameter, ElementNotFound, FailedToCreate
    {
        UserValidator.validate(user);
        AddressValidator.validate(user.getAddress());

        this.userRepository.update(user);
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.userRepository.remove(id);
    }
}
