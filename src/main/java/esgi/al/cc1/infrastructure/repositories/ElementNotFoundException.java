package esgi.al.cc1.infrastructure.repositories;

public class ElementNotFoundException extends Exception
{
    public ElementNotFoundException(Class elementType, String id)
    {
        super("Error: no " + elementType.getSimpleName() + " registered with ID [" + id + "]");
    }
}
