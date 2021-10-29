package esgi.al.models;

import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.enumerators.PaymentMethod;
import java.util.List;
import java.util.UUID;

public interface Users
{
    void add(User user) throws FailedToCreateUser;

    User getById(UUID id) throws NoUserFound;
    User getByLogin(String login) throws NoUserFound;
    List<User> getByName(String name) throws NoUserFound;
    List<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFound;

    void updateById(UUID id) throws FailedToUpdateUser;
    void updateByLogin(UUID id) throws FailedToUpdateUser;

    void deleteById(UUID id) throws NoUserFound;
    void deleteByLogin(UUID id) throws NoUserFound;
}
