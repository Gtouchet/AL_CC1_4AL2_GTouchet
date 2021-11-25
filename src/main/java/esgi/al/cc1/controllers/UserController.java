package esgi.al.cc1.controllers;

import esgi.al.cc1.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.models.Tradesman;
import esgi.al.cc1.repositories.Repository;
import esgi.al.cc1.services.validatorServices.AddressValidator;
import esgi.al.cc1.services.validatorServices.UserValidator;

import java.util.stream.Stream;

public class UserController implements Controller<Tradesman>
{
    private final Repository<Tradesman> userRepository;

    public UserController(Repository<Tradesman> userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public void create(Tradesman tradesman) throws InvalidModelParameter, FailedToCreate
    {
        UserValidator.validate(tradesman);
        AddressValidator.validate(tradesman.getAddress());

        this.userRepository.create(tradesman);
    }

    @Override
    public Stream<Tradesman> read()
    {
        return this.userRepository.read();
    }

    @Override
    public Tradesman read(String id) throws ElementNotFound
    {
        return this.userRepository.read(id);
    }

    @Override
    public void update(Tradesman tradesman) throws InvalidModelParameter, ElementNotFound, FailedToCreate
    {
        UserValidator.validate(tradesman);
        AddressValidator.validate(tradesman.getAddress());

        this.userRepository.update(tradesman);
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.userRepository.remove(id);
    }
}
