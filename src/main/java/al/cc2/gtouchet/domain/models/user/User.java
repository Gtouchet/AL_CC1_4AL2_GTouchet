package al.cc2.gtouchet.domain.models.user;

import al.cc2.gtouchet.domain.models.Entity;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;

public abstract class User extends Entity
{
    private final Credentials credentials;
    private final String name;

    protected User(Id id, Credentials credentials, String name, Date creationDate)
    {
        super(id, creationDate);

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
