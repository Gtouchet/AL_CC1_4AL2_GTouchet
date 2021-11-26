package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.dtos.Date;
import esgi.al.cc1.domain.dtos.Id;
import esgi.al.cc1.domain.dtos.Password;
import esgi.al.cc1.domain.enumerators.Role;

public abstract class User
{
    private final Id id;
    private final String login;
    private Password password;
    private String name;
    private Role role;

    private final Date creationDate;
    private Date updateDate;

    protected User(String login, Password password, String name, Role role)
    {
        this.id = Id.generate();
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;

        this.creationDate = Date.now();
        this.updateDate = Date.now();
    }

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

    public Role getRole()
    {
        return this.role;
    }

    public Date getCreationDate()
    {
        return this.creationDate;
    }

    public Date getUpdateDate()
    {
        return this.updateDate;
    }

    public void setPassword(String password)
    {
        this.password = this.password.set(password);
        this.setUpdateDate();
    }

    public void setName(String name)
    {
        this.name = name;
        this.setUpdateDate();
    }

    public void setRole(Role role)
    {
        this.role = role;
        this.setUpdateDate();
    }

    private void setUpdateDate()
    {
        this.updateDate = Date.now();
    }

    @Override
    public String toString()
    {
        return "ID: " + this.id +
                "\nLogin: " + this.login +
                "\nPassword: " + this.password +
                "\nName: " + this.name +
                "\nRole: " + this.role;
    }
}
