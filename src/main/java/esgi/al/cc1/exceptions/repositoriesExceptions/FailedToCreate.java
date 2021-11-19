package esgi.al.cc1.exceptions.repositoriesExceptions;

public class FailedToCreate extends Throwable
{
    public FailedToCreate(String userLogin, String registeredUserId)
    {
        super("Login [" + userLogin + "]" + " already registered is JSON file with ID [" + registeredUserId + "]");
    }
}