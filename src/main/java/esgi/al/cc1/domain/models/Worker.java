package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

public class Worker extends Entity
{
    private final String login;
    private Password password;
    private String name;
    private Service service;
    private int department;

    private Worker(Id id, String login, Password password, String name, Service service, int department, Date creationDate)
    {
        super(id, creationDate);

        this.login = login;
        this.password = password;
        this.name = name;
        this.service = service;
        this.department = department;
    }

    public static Worker of(Id id, String login, Password password, String name, Service service, int department, Date creationDate)
    {
        return new Worker(id, login, password, name, service, department, creationDate);
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

    public Service getService()
    {
        return this.service;
    }

    public int getDepartment()
    {
        return this.department;
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

    public void setService(Service service)
    {
        this.service = service;
        this.setUpdateDate();
    }

    public void setDepartment(int department)
    {
        this.department = department;
        this.setUpdateDate();
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "\nLogin: " + this.login +
                "\nPassword: " + this.password +
                "\nName: " + this.name +
                "\nService: " + this.service +
                "\nDepartment: " + this.department;
    }
}
