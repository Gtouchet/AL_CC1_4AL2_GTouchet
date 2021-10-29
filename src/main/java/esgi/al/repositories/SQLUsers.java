package esgi.al.repositories;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateException;
import esgi.al.exceptions.FailedToUpdateException;
import esgi.al.exceptions.NoUserFoundException;
import esgi.al.models.User;
import esgi.al.models.Users;
import java.util.List;

public class SQLUsers implements Users
{
    @Override
    public void add(User user) throws FailedToCreateException
    {
        
    }

    @Override
    public User getById(int id) throws NoUserFoundException
    {
        return null;
    }

    @Override
    public User getByLogin(String login) throws NoUserFoundException
    {
        return null;
    }

    @Override
    public List<User> getByName(String name) throws NoUserFoundException
    {
        return null;
    }

    @Override
    public List<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFoundException
    {
        return null;
    }

    @Override
    public void updateById(int id) throws FailedToUpdateException
    {

    }

    @Override
    public void updateByLogin(int id) throws FailedToUpdateException
    {

    }

    @Override
    public void deleteById(int id) throws NoUserFoundException
    {

    }

    @Override
    public void deleteByLogin(int id) throws NoUserFoundException
    {

    }
}
