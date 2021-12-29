package al.cc2.gtouchet.domain.models.user;

import al.cc2.gtouchet.domain.models.Entity;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public abstract class User extends Entity
{
    private final Credentials credentials;
    private final String name;

    protected User(EntityId id, Credentials credentials, String name, Clock creationClock)
    {
        super(id, creationClock);

        this.credentials = credentials;
        this.name = name;
    }

    public Credentials getCredentials()
    {
        return this.credentials;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                this.credentials.toString() +
                "\nName: " + this.name;
    }
}
