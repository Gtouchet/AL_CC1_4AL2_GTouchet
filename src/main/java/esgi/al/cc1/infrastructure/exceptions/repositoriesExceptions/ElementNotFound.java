package esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions;

public class ElementNotFound extends Exception
{
    public ElementNotFound(Class elementType, String id)
    {
        super("Error: no " + elementType.getSimpleName() + " registered with ID [" + id + "]");
    }
}
