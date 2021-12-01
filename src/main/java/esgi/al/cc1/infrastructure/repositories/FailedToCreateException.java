package esgi.al.cc1.infrastructure.repositories;

public class FailedToCreateException extends Exception
{
    public FailedToCreateException(Class elementType, String reason)
    {
        super("Error: failed to create new " + elementType.getSimpleName() + ", " + reason);
    }
}
