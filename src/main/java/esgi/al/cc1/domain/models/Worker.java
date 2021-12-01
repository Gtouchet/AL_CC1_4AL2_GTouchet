package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

public class Worker extends User
{
    private Service service;
    private int department;

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
                "\nService: " + this.service +
                "\nDepartment: " + this.department;
    }
}
