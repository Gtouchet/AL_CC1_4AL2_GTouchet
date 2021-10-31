package esgi.al.repositories;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.utils.JsonHelper;
import esgi.al.utils.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UsersRepository implements Users
{
    private final List<User> users = new ArrayList<>();
    Supplier<Stream<User>> streamSupplier = () -> Stream.of(this.users.toArray(new User[0]));

    @Override
    public void create(User newUser, Boolean isJson) throws FailedToCreateUser
    {
        User registeredUser = this.streamSupplier.get()
                .filter(user -> user.getLogin().equals(newUser.getLogin()))
                .findFirst()
                .orElse(null);

        if (registeredUser != null)
        {
            throw new FailedToCreateUser(newUser.getLogin(), registeredUser.getId());
        }

        this.users.add(newUser);
        if (!isJson)
        {
            JsonHelper.rewriteFile(this.users);
        }
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
    public User getById(String id) throws NoUserFound
    {
        User user = this.users.stream()
                .filter(streamUser -> streamUser.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user == null)
        {
            throw new NoUserFound(id);
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
    public Stream<User> getByPaymentMethod(String paymentMethod) throws NoUserFound
    {
        if (!Validator.isPaymentMethodValid(paymentMethod))
        {
            System.out.println("Unknown payment method [" + paymentMethod + "], it should be 'card', 'paypal' or 'unspecified'");
            return Stream.empty();
        }

        PaymentMethod method = PaymentMethod.valueOf(paymentMethod);
        if (this.streamSupplier.get().noneMatch(user -> user.getPaymentMethod().equals(method)))
        {
            throw new NoUserFound(paymentMethod);
        }

        return this.streamSupplier.get()
                .filter(user -> user.getPaymentMethod().equals(method));
    }

    @Override
    public void updatePasswordBy(String isId, String idOrLogin, String newPassword) throws NoUserFound, FailedToUpdateUser
    {
        if (!isId.equals("ID") && !isId.equals("LOGIN"))
        {
            throw new FailedToUpdateUser(idOrLogin, "invalid get method ('ID' or 'LOGIN')");
        }

        if (!Validator.isPasswordValid(newPassword))
        {
            throw new FailedToUpdateUser(idOrLogin, "invalid password");
        }
        User user = isId.equals("ID") ? this.getById(idOrLogin) : this.getByLogin(idOrLogin);
        this.users.remove(user);
        user.setPassword(newPassword);
        this.users.add(user);
        JsonHelper.rewriteFile(this.users);
    }

    @Override
    public void updateNameBy(String isId, String idOrLogin, String newName) throws NoUserFound, FailedToUpdateUser
    {
        if (!isId.equals("ID") && !isId.equals("LOGIN"))
        {
            throw new FailedToUpdateUser(idOrLogin, "invalid get method ('ID' or 'LOGIN')");
        }

        User user = isId.equals("ID") ? this.getById(idOrLogin) : this.getByLogin(idOrLogin);
        this.users.remove(user);
        user.setName(newName);
        this.users.add(user);
        JsonHelper.rewriteFile(this.users);
    }

    @Override
    public void deleteBy(String isId, String idOrLogin) throws NoUserFound, FailedToUpdateUser
    {
        if (!isId.equals("ID") && !isId.equals("LOGIN"))
        {
            throw new FailedToUpdateUser(idOrLogin, "invalid get method ('ID' or 'LOGIN')");
        }

        User user = isId.equals("ID") ? this.getById(idOrLogin) : this.getByLogin(idOrLogin);
        this.users.remove(user);
        JsonHelper.rewriteFile(this.users);
    }
}
