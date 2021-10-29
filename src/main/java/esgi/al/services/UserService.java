package esgi.al.services;

import esgi.al.exceptions.FailedToCreateUser;
import esgi.al.exceptions.NoUserFound;
import esgi.al.models.User;
import esgi.al.models.Users;
import java.util.UUID;

public class UserService
{
    private final Users users;

    public UserService(Users users)
    {
        this.users = users;
    }

    public void createUser(User user) throws FailedToCreateUser
    {
       this.users.add(user);
    }

    public User getById(UUID id) throws NoUserFound
    {
        return this.users.getById(id);
    }

    public void updatePassword(UUID id, String newPassword) throws NoUserFound, FailedToCreateUser
    {
        final User user = users.getById(id);
        user.updatePassword(newPassword);
        this.users.add(user);
    }
}
