package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

public abstract class User extends Entity
{
    private final String login;
    private Password password;
    private String name;

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

    public void setPassword(Password password)
    {
        this.password = password;
        this.setUpdateDate();
    }

    public void setName(String name)
    {
        this.name = name;
        this.setUpdateDate();
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
