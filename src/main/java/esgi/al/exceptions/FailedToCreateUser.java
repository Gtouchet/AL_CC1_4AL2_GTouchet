package esgi.al.exceptions;

public class FailedToCreateUser extends Throwable
{
    public FailedToCreateUser(String userLogin, String registeredUserId)
    {
        super(
                "Login [" + userLogin + "]" +
                " already registered is JSON file with ID [" + registeredUserId + "]"
        );
    }
}