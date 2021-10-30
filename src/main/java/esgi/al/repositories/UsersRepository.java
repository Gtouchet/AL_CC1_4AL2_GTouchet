package esgi.al.repositories;

import esgi.al.Globals;
import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.utils.JsonHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

// Todo: FailedToCreateUser and FailedToUpdateUser exceptions
public class UsersRepository implements Users
{
    private final List<User> users = new ArrayList<>();
    Supplier<Stream<User>> streamSupplier = () -> Stream.of(this.users.toArray(new User[0]));

    @Override
    public void create(User user) throws FailedToCreateUser
    {
        for (User registeredUser : this.users)
        {
            if (registeredUser.getLogin().equals(user.getLogin()))
            {
                throw new FailedToCreateUser(user.getLogin(), registeredUser.getId());
            }
        }
        this.users.add(user);
    }

    @Override
    public Stream<User> getAll() throws NoUserFound
    {
        if (this.streamSupplier.get().count() == 0)
        {
            throw new NoUserFound();
        }
        return this.streamSupplier.get();
    }

    @Override
    public User getById(UUID id) throws NoUserFound
    {
        User user = this.users.stream()
                .filter(streamUser -> streamUser.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user == null)
        {
            throw new NoUserFound(id.toString());
        }
        return user;
    }

    @Override
    public User getByLogin(String login) throws NoUserFound
    {
        User user = this.users.stream()
                .filter(streamUser -> streamUser.getLogin().equals(login))
                .findFirst()
                .orElse(null);

        if (user == null)
        {
            throw new NoUserFound(login);
        }
        return user;
    }

    @Override
    public Stream<User> getByName(String name) throws NoUserFound
    {
        if (this.streamSupplier.get().noneMatch(user -> user.getName().equals(name)))
        {
            throw new NoUserFound(name);
        }
        return this.streamSupplier.get()
                .filter(user -> user.getName().equals(name));
    }

    @Override
    public Stream<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFound
    {
        if (this.streamSupplier.get().noneMatch(user -> user.getPaymentMethod().equals(paymentMethod)))
        {
            throw new NoUserFound(paymentMethod.toString());
        }
        return this.streamSupplier.get()
                .filter(user -> user.getPaymentMethod().equals(paymentMethod));
    }

    @Override
    public void updateById(UUID id, User newUser) throws NoUserFound, FailedToUpdateUser
    {
        User user = this.users.stream()
                .filter(streamUser -> streamUser.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user == null)
        {
            throw new NoUserFound(id.toString());
        }

        // Todo: implements update
    }

    @Override
    public void updateByLogin(String login, User newUser) throws NoUserFound, FailedToUpdateUser
    {
        User user = this.users.stream()
                .filter(streamUser -> streamUser.getLogin().equals(login))
                .findFirst()
                .orElse(null);

        if (user == null)
        {
            throw new NoUserFound(login);
        }

        // Todo: implements update
    }

    @Override
    public void deleteById(UUID id) throws NoUserFound
    {
        User user = this.users.stream()
                .filter(streamUser -> streamUser.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user == null)
        {
            throw new NoUserFound(id.toString());
        }

        JsonHelper.deleteUserFromFile(Globals.JSON_USER_FILE_PATH, user.getId());
    }

    @Override
    public void deleteByLogin(String login) throws NoUserFound
    {
        User user = this.users.stream()
                .filter(streamUser -> streamUser.getLogin().equals(login))
                .findFirst()
                .orElse(null);

        if (user == null)
        {
            throw new NoUserFound(login);
        }

        JsonHelper.deleteUserFromFile(Globals.JSON_USER_FILE_PATH, user.getId());
    }
}
