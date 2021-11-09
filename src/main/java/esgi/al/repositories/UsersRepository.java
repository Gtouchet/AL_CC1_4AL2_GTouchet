package esgi.al.repositories;

import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.models.User;
import esgi.al.utilitaries.JsonHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UsersRepository implements Repository<User>
{
    private final List<User> users;

    private final JsonHelper<User> jsonHelper;

    public UsersRepository(String jsonFilePath)
    {
        this.jsonHelper = new JsonHelper<>(User.class, jsonFilePath);

        this.users = this.getDataFromJsonFile();
    }

    private List<User> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonHelper.getDataFromFile()));
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

        this.jsonHelper.writeInFile(this.users);
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

        this.jsonHelper.writeInFile(this.users);
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        User user = this.findUserById(id);

        this.users.remove(user);

        this.jsonHelper.writeInFile(this.users);
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
