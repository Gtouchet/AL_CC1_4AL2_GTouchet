package esgi.al.exceptions;

public class FailedToUpdateUser extends Throwable
{
    public FailedToUpdateUser(String idOrLogin, String reason)
    {
        super("Impossible to update user [" + idOrLogin + "], " + reason);
    }
}
