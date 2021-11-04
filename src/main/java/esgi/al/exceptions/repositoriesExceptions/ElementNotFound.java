package esgi.al.exceptions.repositoriesExceptions;

public class ElementNotFound extends Throwable
{
    public ElementNotFound(String id)
    {
        super("No user registered with ID [" + id + "]");
    }
}
