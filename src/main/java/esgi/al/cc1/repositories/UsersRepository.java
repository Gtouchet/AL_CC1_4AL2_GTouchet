package esgi.al.cc1.repositories;

import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.services.jsonServices.JsonAccessor;
import esgi.al.cc1.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UsersRepository implements Repository<User>
{
    private final List<User> users;

    private final JsonAccessor<User> jsonAccessor;

    public UsersRepository(JsonAccessor<User> jsonAccessor)
    {
        this.jsonAccessor = jsonAccessor;

        this.users = this.getDataFromJsonFile();
    }

    private List<User> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonAccessor.getDataFromFile()));
    }

    @Override
    public void create(User user) throws FailedToCreate
    {
        User registeredUser = this.findUserByLogin(user.getLogin());
        if (registeredUser != null)
        {
            throw new FailedToCreate(user.getLogin(), registeredUser.getId());
        }

        this.users.add(user);

        this.jsonAccessor.writeInFile(this.users);
    }

    @Override
    public Stream<User> read()
    {
        return this.users.stream();
    }

    @Override
    public User read(String id) throws ElementNotFound
    {
        return this.findUserById(id);
    }

    @Override
    public void update(User user) throws ElementNotFound, FailedToCreate
    {
        User registeredUserId = this.findUserById(user.getId());

        User registeredUserLogin = this.findUserByLogin(user.getLogin());
        if (registeredUserLogin != null && !registeredUserLogin.getLogin().equals(user.getLogin()))
        {
            throw new FailedToCreate(user.getLogin(), registeredUserLogin.getId());
        }

        this.users.remove(registeredUserId);
        this.users.add(user);

        this.jsonAccessor.writeInFile(this.users);
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        User user = this.findUserById(id);

        this.users.remove(user);

        this.jsonAccessor.writeInFile(this.users);
    }

    private User findUserById(String id) throws ElementNotFound
    {
        User user = this.users.stream()
                .filter(streamUser -> streamUser.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user == null)
        {
            throw new ElementNotFound(id);
        }

        return user;
    }

    private User findUserByLogin(String login)
    {
        return this.users.stream()
                .filter(streamUser -> streamUser.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }
}
