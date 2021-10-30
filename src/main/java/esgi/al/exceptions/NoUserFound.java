package esgi.al.exceptions;

public class NoUserFound extends Throwable
{
    public NoUserFound()
    {
        super("No user registered");
    }

    public NoUserFound(String value)
    {
        super("No user registered with ID, login, name or payment method [" + value + "]");
    }
}
