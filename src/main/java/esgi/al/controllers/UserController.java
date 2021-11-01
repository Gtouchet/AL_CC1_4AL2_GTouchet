package esgi.al.controllers;

import esgi.al.daos.UserDao;
import esgi.al.exceptions.modelsExceptions.InvalidAddressParameter;
import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.repositories.UsersRepository;
import esgi.al.validators.AddressValidator;
import esgi.al.validators.UserValidator;

import java.util.stream.Stream;

public class UserController implements Controllers<UserDao>
{
    private final UsersRepository userRepository;

    public UserController(UsersRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void register(UserDao user)
    {
        try {
            UserValidator.validate(user);
            AddressValidator.validate(user.address);

            this.userRepository.register(user);

        } catch (InvalidUserParameter | InvalidAddressParameter e) {
            e.printStackTrace();
        }
    }

    @Override
    public void post(UserDao user)
    {
        try {
            UserValidator.validate(user);
            AddressValidator.validate(user.address);

            this.userRepository.post(user);

        } catch (InvalidUserParameter | InvalidAddressParameter | FailedToCreate e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Stream<UserDao> get()
    {
        try {
            return this.userRepository.get();

        } catch (ElementNotFound e) {
            System.err.println(e.getMessage());
            return Stream.empty();
        }
    }

    @Override
    public UserDao get(String id)
    {
        try {
            return this.userRepository.get(id);

        } catch (ElementNotFound e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void put(String id, UserDao user)
    {
        try {
            UserValidator.validate(user);
            AddressValidator.validate(user.address);

            this.userRepository.put(id, user);

        } catch (InvalidUserParameter | InvalidAddressParameter | ElementNotFound e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void del(String id)
    {
        try {
            this.userRepository.del(id);

        } catch (ElementNotFound e) {
            System.err.println(e.getMessage());
        }
    }
}
