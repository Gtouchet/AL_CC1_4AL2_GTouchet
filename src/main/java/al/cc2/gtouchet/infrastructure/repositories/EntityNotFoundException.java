package al.cc2.gtouchet.infrastructure.repositories;

import al.cc2.gtouchet.domain.valueObjects.EntityId;

public class EntityNotFoundException extends RuntimeException
{
    public EntityNotFoundException(EntityId id)
    {
        super("Error: no desired entity registered with ID [" + id + "]");
    }
}
