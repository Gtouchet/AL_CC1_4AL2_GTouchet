package esgi.al.factories;

import esgi.al.models.User;
import esgi.al.repositories.Repository;
import esgi.al.repositories.UsersRepository;
import esgi.al.utilitaries.Globals;

public class RepositoriesFactory
{
    public Repository<User> createUserRepository()
    {
        return new UsersRepository(Globals.JSON_USER_FILE_PATH);
    }
}