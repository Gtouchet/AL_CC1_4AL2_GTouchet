package esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions;

public class FailedToCreate extends Exception
{
    public FailedToCreate(Class elementType, String reason)
    {
        super("Error: failed to create new " + elementType.getSimpleName() + ", " + reason);
    }
}
