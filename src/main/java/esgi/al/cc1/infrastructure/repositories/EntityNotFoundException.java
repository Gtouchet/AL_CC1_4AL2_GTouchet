package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.valueObjects.Id;

public class EntityNotFoundException extends Exception
{
    public EntityNotFoundException(Id id)
    {
        super("Error: no desired entity registered with ID [" + id + "]");
    }
}
