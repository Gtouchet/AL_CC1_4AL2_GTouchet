package al.cc2.gtouchet.infrastructure.repositories;

import al.cc2.gtouchet.domain.valueObjects.Id;

public class EntityNotFoundException extends Exception
{
    public EntityNotFoundException(Id id)
    {
        super("Error: no desired entity registered with ID [" + id + "]");
    }
}
