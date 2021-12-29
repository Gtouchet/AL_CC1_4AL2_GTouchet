package al.cc2.gtouchet.domain.models;

import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public abstract class Entity
{
    private final EntityId id;
    private final Clock creationClock;
    private final Clock updateClock;

    protected Entity(EntityId id, Clock creationClock)
    {
        this.id = id;
        this.creationClock = creationClock;
        this.updateClock = Clock.now();
    }

    public EntityId getId()
    {
        return this.id;
    }

    public Clock getCreationDate()
    {
        return this.creationClock;
    }

    public Clock getUpdateDate()
    {
        return this.updateClock;
    }

    @Override
    public String toString()
    {
        return "Id: " + this.id;
    }
}
