package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.dtos.Password;
import esgi.al.cc1.domain.enumerators.Role;
import esgi.al.cc1.domain.enumerators.Service;

public class Worker extends User
{
    private Service service;
    private int department;

    private Worker(String login, Password password, String name, Role role, Service service, int department)
    {
        super(login, password, name, role);

        this.service = service;
        this.department = department;
    }

    public static Worker of(String login, Password password, String name, Role role, Service service, int department)
    {
        return new Worker(login, password, name, role, service, department);
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
    }

    public void setDepartment(int department)
    {
        this.department = department;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "\nService: " + this.service +
                "\nDepartment: " + this.department;
    }
}
