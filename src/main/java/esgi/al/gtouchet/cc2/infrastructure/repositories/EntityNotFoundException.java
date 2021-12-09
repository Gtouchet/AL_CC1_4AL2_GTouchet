package esgi.al.gtouchet.cc2.infrastructure.repositories;

import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class EntityNotFoundException extends Exception
{
    public EntityNotFoundException(Id id)
    {
        super("Error: no desired entity registered with ID [" + id + "]");
    }
}
