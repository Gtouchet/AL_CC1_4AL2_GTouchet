package esgi.al.controllers;

import esgi.al.exceptions.modelsExceptions.InvalidAddressParameter;
import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
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
    public void post(User user) throws InvalidUserParameter, InvalidAddressParameter, FailedToCreate
    {
        UserValidator.validate(user);
        AddressValidator.validate(user.getAddress());

        this.userRepository.post(user);
    }

    @Override
    public Stream<User> get()
    {
        return this.userRepository.get();
    }

    @Override
    public User get(String id) throws ElementNotFound
    {
        return this.userRepository.get(id);
    }

    @Override
    public void put(User user) throws InvalidUserParameter, InvalidAddressParameter, FailedToCreate, ElementNotFound
    {
        UserValidator.validate(user);
        AddressValidator.validate(user.getAddress());

        this.userRepository.put(user);
    }

    @Override
    public void del(String id) throws ElementNotFound
    {
        this.userRepository.del(id);
    }
}
