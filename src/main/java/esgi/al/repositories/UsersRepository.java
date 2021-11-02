package esgi.al.repositories;

import esgi.al.daos.UserDao;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.models.User;
import esgi.al.utils.JsonHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UsersRepository implements Repositories<User, UserDao>
{
    private final List<User> users = new ArrayList<>();
    Supplier<Stream<User>> usersStreamSupplier = () -> Stream.of(this.users.toArray(new User[0]));

    public void register(UserDao user)
    {
        this.users.add(User.of(user));
    }

    @Override
    public void post(UserDao user) throws FailedToCreate
    {
        UserDao registeredUser = this.findUserWithLogin(user.login);
        if (registeredUser != null)
        {
            throw new FailedToCreate(user.login, registeredUser.id);
        }

        this.users.add(User.of(user));

        JsonHelper.rewriteFile(this.users);
    }

    @Override
    public Stream<User> get() throws ElementNotFound
    {
        if (this.usersStreamSupplier.get().count() == 0)
        {
            throw new ElementNotFound();
        }

        return this.usersStreamSupplier.get();
    }

    @Override
    public User get(String id) throws ElementNotFound
    {
        return this.findUserWithId(id);
    }

    @Override
    public void put(String id, UserDao user) throws ElementNotFound, FailedToCreate
    {
        UserDao registeredUserId = this.findUserWithId(id);

        UserDao registeredUserLogin = this.findUserWithLogin(user.login);
        if (registeredUserLogin != null && registeredUserLogin.login.equals(user.login))
        {
            throw new FailedToCreate(user.login, registeredUserLogin.id);
        }

        this.users.remove(registeredUserId);
        user.id = registeredUserId.id;
        this.users.add(User.of(user));

        JsonHelper.rewriteFile(this.users);
    }

    @Override
    public void del(String id) throws ElementNotFound
    {
        UserDao registeredUser = this.findUserWithId(id);

        this.users.remove(registeredUser);

        JsonHelper.rewriteFile(this.users);
    }

    private User findUserWithId(String id) throws ElementNotFound
    {
        User user = this.users.stream()
                .filter(streamUserDao -> streamUserDao.id.equals(id))
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
                .filter(streamUserDao -> streamUserDao.login.equals(login))
                .findFirst()
                .orElse(null);
    }
}
