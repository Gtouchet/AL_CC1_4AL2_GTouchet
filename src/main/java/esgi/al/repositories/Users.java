package esgi.al.repositories;

import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.FailedToUpdateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;

import java.util.stream.Stream;

public interface Users
{
    void create(User newUser, Boolean isJson) throws FailedToCreateUser;

    Stream<User> getAll() throws NoUserFound;
    User getById(String id) throws NoUserFound;
    User getByLogin(String login) throws NoUserFound;
    Stream<User> getByName(String name) throws NoUserFound;
    Stream<User> getByPaymentMethod(String paymentMethod) throws NoUserFound;

    void updatePasswordBy(String isId, String idOrLogin, String newPassword) throws NoUserFound, FailedToUpdateUser;
    void updateNameBy(String isId, String idOrLogin, String newName) throws NoUserFound, FailedToUpdateUser;

    void deleteBy(String isId, String idOrLogin) throws NoUserFound, FailedToUpdateUser;
}
