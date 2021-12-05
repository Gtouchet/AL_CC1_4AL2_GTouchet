package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

public class Worker extends User
{
    private final Service service;
    private final int department;

    private Worker(Id id, String login, Password password, String name, Service service, int department, Date creationDate)
    {
        super(id, login, password, name, creationDate);

        this.service = service;
        this.department = department;
    }

    public static Worker of(Id id, String login, Password password, String name, Service service, int department, Date creationDate)
    {
        return new Worker(id, login, password, name, service, department, creationDate);
    }

    public Service getService()
    {
        return this.service;
    }

    public int getDepartment()
    {
        return this.department;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "\nService: " + this.service +
                "\nDepartment: " + this.department;
    }
}
