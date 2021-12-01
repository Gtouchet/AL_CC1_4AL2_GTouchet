package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

public abstract class User implements Entity
{
    private final Id id;
    private final String login;
    private Password password;
    private String name;

    private final Date creationDate;
    private Date updateDate;

    protected User(Id id, String login, Password password, String name, Date creationDate)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;

        this.creationDate = creationDate;
        this.updateDate = Date.now();
    }

    @Override
    public Id getId()
    {
        return this.id;
    }

    public String getLogin()
    {
        return this.login;
    }

    public Password getPassword()
    {
        return this.password;
    }

    public String getName()
    {
        return this.name;
    }

    public Date getCreationDate()
    {
        return this.creationDate;
    }

    public Date getUpdateDate()
    {
        return this.updateDate;
    }

    public void setPassword(Password password)
    {
        this.password = Password.of(password);
        this.setUpdateDate();
    }

    public void setName(String name)
    {
        this.name = name;
        this.setUpdateDate();
    }

    protected void setUpdateDate()
    {
        this.updateDate = Date.now();
    }

    @Override
    public String toString()
    {
        return "ID: " + this.id +
                "\nLogin: " + this.login +
                "\nPassword: " + this.password +
                "\nName: " + this.name;
    }
}
