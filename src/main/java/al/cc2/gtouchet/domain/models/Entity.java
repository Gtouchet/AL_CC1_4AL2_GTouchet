package al.cc2.gtouchet.domain.models;

import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;

public abstract class Entity
{
    private final Id id;
    private final Date creationDate;
    private final Date updateDate;

    protected Entity(Id id, Date creationDate)
    {
        this.id = id;
        this.creationDate = creationDate;
        this.updateDate = Date.now();
    }

    public Id getId()
    {
        return this.id;
    }

    public Date getCreationDate()
    {
        return this.creationDate;
    }

    public Date getUpdateDate()
    {
        return this.updateDate;
    }

    @Override
    public String toString()
    {
        return "Id: " + this.id;
    }
}