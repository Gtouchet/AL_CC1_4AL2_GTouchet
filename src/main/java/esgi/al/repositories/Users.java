package esgi.al.repositories;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;

import java.util.UUID;
import java.util.stream.Stream;

public interface Users
{
    void create(User user) throws FailedToCreateUser;

    Stream<User> getAll() throws NoUserFound;
    User getById(UUID id) throws NoUserFound;
    User getByLogin(String login) throws NoUserFound;
    Stream<User> getByName(String name) throws NoUserFound;
    Stream<User> getByPaymentMethod(PaymentMethod paymentMethod) throws NoUserFound;

    void updatePasswordBy(Boolean isId, String idOrLogin, String newPassword) throws NoUserFound, FailedToUpdateUser;
    void updateNameBy(Boolean isId, String idOrLogin, String newName) throws NoUserFound, FailedToUpdateUser;

    void deleteById(UUID id) throws NoUserFound;
    void deleteByLogin(String login) throws NoUserFound;
}
