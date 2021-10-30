package esgi.al.repositories;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.models.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

// Todo: FailedToCreateUser and FailedToUpdateUser exceptions
// Todo: also implements exceptions I guess
public class SQLUsers implements Users
{
    private final List<User> users = new ArrayList<>();
    Supplier<Stream<User>> streamSupplier = () -> Stream.of(this.users.toArray(new User[0]));

    @Override
    public void add(User user) throws FailedToCreateUser
    {
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
            throw new NoUserFound();
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
            throw new NoUserFound();
        }
        return user;
    }

    @Override
    public Stream<User> getByName(String name) throws NoUserFound
    {
        if (this.streamSupplier.get().noneMatch(user -> user.getName().equals(name)))
        {
            throw new NoUserFound();
        }
        return this.streamSupplier.get()
                .filter(user -> user.getName().equals(name));
    }

    @Override
    public Stream<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFound
    {
        if (this.streamSupplier.get().noneMatch(user -> user.getPaymentMethod().equals(paymentMethod)))
        {
            throw new NoUserFound();
        }
        return this.streamSupplier.get()
                .filter(user -> user.getPaymentMethod().equals(paymentMethod));
    }

    @Override
    public void updateById(UUID id, User user) throws FailedToUpdateUser
    {

    }

    @Override
    public void updateByLogin(String login, User user) throws FailedToUpdateUser
    {

    }

    @Override
    public void deleteById(UUID id) throws NoUserFound
    {

    }

    @Override
    public void deleteByLogin(String login) throws NoUserFound
    {

    }
}
