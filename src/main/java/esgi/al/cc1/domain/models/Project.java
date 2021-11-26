package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.dtos.Date;
import esgi.al.cc1.domain.dtos.Id;

import java.util.ArrayList;
import java.util.List;

public class Project
{
    private final Id id;
    private int department;
    private final List<Id> workersId;

    private final Date creationDate;
    private Date updateDate;

    private Project(int department)
    {
        this.id = Id.generate();
        this.department = department;
        this.workersId = new ArrayList<>();

        this.creationDate = Date.now();
        this.updateDate = Date.now();
    }

    public static Project of(int department)
    {
        return new Project(department);
    }

    public Id getId()
    {
        return this.id;
    }

    public int getDepartment()
    {
        return this.department;
    }

    public List<Id> getWorkersId()
    {
        return this.workersId;
    }

    public Date getCreationDate()
    {
        return this.creationDate;
    }

    public Date getUpdateDate()
    {
        return this.updateDate;
    }

    public void setDepartment(int department)
    {
        this.department = department;
        this.setUpdateDate();
    }

    public void addWorker(Id workerId)
    {
        this.workersId.add(workerId);
        this.setUpdateDate();
    }

    public void removeWorker(Id workerId)
    {
        this.workersId.remove(workerId);
        this.setUpdateDate();
    }

    private void setUpdateDate()
    {
        this.updateDate = Date.now();
    }
}
