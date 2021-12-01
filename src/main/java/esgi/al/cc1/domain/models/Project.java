package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;

import java.util.ArrayList;
import java.util.List;

public class Project implements Entity
{
    private final Id id;
    private Id contractorId;
    private int department;
    private final List<Id> workersId;

    private final Date creationDate;
    private Date updateDate;

    private Project(Id id, Id contractorId, int department, Date creationDate)
    {
        this.id = id;
        this.contractorId = contractorId;
        this.department = department;
        this.workersId = new ArrayList<>();

        this.creationDate = creationDate;
        this.updateDate = Date.now();
    }

    public static Project of(Id id, Id contractorId, int department, Date creationDate)
    {
        return new Project(id, contractorId, department, creationDate);
    }

    @Override
    public Id getId()
    {
        return this.id;
    }

    public Id getContractorId()
    {
        return this.contractorId;
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

    public void setContractorId(Id id)
    {
        this.contractorId = Id.fromString(id.toString());
    }

    public void setDepartment(int department)
    {
        this.department = department;
        this.setUpdateDate();
    }

    public void addWorker(Worker worker)
    {
        this.workersId.add(worker.getId());
        this.setUpdateDate();
    }

    public void removeWorker(Worker worker)
    {
        this.workersId.remove(worker.getId());
        this.setUpdateDate();
    }

    private void setUpdateDate()
    {
        this.updateDate = Date.now();
    }

    @Override
    public String toString()
    {
        StringBuilder project = new StringBuilder(
                "ID: " + this.id +
                "\nContractor ID: " + this.contractorId +
                "\nDepartment: " + this.department +
                "\nWorkers IDs: "
        );

        if (this.workersId.size() == 0)
        {
            project.append("None");
        }
        else
        {
            this.workersId.stream().map(project::append);
        }

        return project.toString();
    }
}
