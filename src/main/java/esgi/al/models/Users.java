package esgi.al.models;

import esgi.al.exceptions.FailedToCreateException;
import esgi.al.exceptions.NoUserFoundException;
import esgi.al.exceptions.FailedToUpdateException;
import esgi.al.enumerators.PaymentMethod;
import java.util.List;

public interface Users
{
    void add(User user) throws FailedToCreateException;

    User getById(int id) throws NoUserFoundException;
    User getByLogin(String login) throws NoUserFoundException;
    List<User> getByName(String name) throws NoUserFoundException;
    List<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFoundException;

    void updateById(int id) throws FailedToUpdateException;
    void updateByLogin(int id) throws FailedToUpdateException;

    void deleteById(int id) throws NoUserFoundException;
    void deleteByLogin(int id) throws NoUserFoundException;
}
