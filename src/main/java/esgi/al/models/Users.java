package esgi.al.models;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;

import java.util.UUID;
import java.util.stream.Stream;

public interface Users
{
    void add(User user) throws FailedToCreateUser;

    Stream<User> getAll() throws NoUserFound;
    User getById(UUID id) throws NoUserFound;
    User getByLogin(String login) throws NoUserFound;
    Stream<User> getByName(String name) throws NoUserFound;
    Stream<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFound;

    void updateById(UUID id, User user) throws FailedToUpdateUser;
    void updateByLogin(String login, User user) throws FailedToUpdateUser;

    void deleteById(UUID id) throws NoUserFound;
    void deleteByLogin(String login) throws NoUserFound;
}
