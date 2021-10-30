package esgi.al.exceptions;

import java.util.UUID;

public class FailedToCreateUser extends Throwable
{
    public FailedToCreateUser(String userLogin, UUID registeredUserId)
    {
        super(
                "Login [" + userLogin + "]" +
                " already registered is JSON file with ID [" + registeredUserId + "]"
        );
    }
}
