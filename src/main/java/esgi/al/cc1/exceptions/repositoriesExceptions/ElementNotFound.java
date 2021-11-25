package esgi.al.cc1.exceptions.repositoriesExceptions;

public class ElementNotFound extends Exception
{
    public ElementNotFound(String id)
    {
        super("No element registered with ID [" + id + "]");
    }
}
