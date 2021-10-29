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

// Todo: implements all method you lazy nerd >:/
public class SQLUsers implements Users
{
    private final List<User> users = new ArrayList<User>();

    @Override
    public void add(User user) throws FailedToCreateUser
    {
        this.users.add(user);
    }

    @Override
    public User getById(UUID id) throws NoUserFound
    {
        for (User user: this.users)
        {
            if (user.getId() == id)
            {
                return user;
            }
        }
        throw new NoUserFound();
    }

    @Override
    public User getByLogin(String login) throws NoUserFound
    {
        return null;
    }

    @Override
    public List<User> getByName(String name) throws NoUserFound
    {
        return null;
    }

    @Override
    public List<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFound
    {
        return null;
    }

    @Override
    public void updateById(UUID id) throws FailedToUpdateUser
    {

    }

    @Override
    public void updateByLogin(UUID id) throws FailedToUpdateUser
    {

    }

    @Override
    public void deleteById(UUID id) throws NoUserFound
    {

    }

    @Override
    public void deleteByLogin(UUID id) throws NoUserFound
    {

    }
}
