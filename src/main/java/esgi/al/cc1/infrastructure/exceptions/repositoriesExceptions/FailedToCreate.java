package esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions;

public class FailedToCreate extends Exception
{
    public FailedToCreate(Class elementType)
    {
        super("Error: failed to create new " + elementType.getSimpleName() + ", login already in use");
    }
}
