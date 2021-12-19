package al.cc2.gtouchet.domain.models;

import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.domain.valueObjects.Password;

public abstract class User extends Entity
{
    private final String login;
    private final Password password;
    private final String name;

    protected User(Id id, String login, Password password, String name, Date creationDate)
    {
        super(id, creationDate);

        this.login = login;
        this.password = password;
        this.name = name;
    }

    public String getLogin()
    {
        return login;
    }

    public Password getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "\nLogin: " + this.login +
                "\nPassword: " + this.password +
                "\nName: " + this.name;
    }
}
