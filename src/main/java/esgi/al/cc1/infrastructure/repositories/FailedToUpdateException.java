package esgi.al.cc1.infrastructure.repositories;

public class FailedToUpdateException extends Exception
{
    public FailedToUpdateException(Class elementType, String id)
    {
        super("Error: failed to update " + elementType + " ID [" + id +"]");
    }
}
