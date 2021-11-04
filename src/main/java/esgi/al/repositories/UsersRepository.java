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
    public void post(User user) throws FailedToCreate
    {
        User registeredUser = this.findUserWithLogin(user.getLogin());
        if (registeredUser != null)
        {
            throw new FailedToCreate(user.getLogin(), registeredUser.getId());
        }

        this.users.add(User.of(user));

        this.jsonHelper.writeInFile(this.users);
    }

    @Override
    public Stream<User> get()
    {
        return this.users.stream();
    }

    @Override
    public User get(String id) throws ElementNotFound
    {
        return this.findUserWithId(id);
    }

    @Override
    public void put(User user) throws ElementNotFound, FailedToCreate
    {
        User registeredUserId = this.findUserWithId(user.getId());

        User registeredUserLogin = this.findUserWithLogin(user.getLogin());
        if (registeredUserLogin != null && !registeredUserLogin.getLogin().equals(user.getLogin()))
        {
            throw new FailedToCreate(user.getLogin(), registeredUserLogin.getId());
        }

        this.users.remove(registeredUserId);
        this.users.add(User.of(user));

        this.jsonHelper.writeInFile(this.users);
    }

    @Override
    public void del(String id) throws ElementNotFound
    {
        User registeredUser = this.findUserWithId(id);

        this.users.remove(registeredUser);

        this.jsonHelper.writeInFile(this.users);
    }

    private User findUserWithId(String id) throws ElementNotFound
    {
        User user = this.users.stream()
                .filter(streamUserDao -> streamUserDao.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user == null)
        {
            throw new ElementNotFound(id);
        }

        return user;
    }

    private User findUserWithLogin(String login)
    {
        return this.users.stream()
                .filter(streamUserDao -> streamUserDao.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }
}
