package esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions;

public class FailedToCreate extends Exception
{
    public FailedToCreate(String userLogin, String registeredUserId)
    {
        super("Login [" + userLogin + "]" + " already registered is JSON file with ID [" + registeredUserId + "]");
    }
}
