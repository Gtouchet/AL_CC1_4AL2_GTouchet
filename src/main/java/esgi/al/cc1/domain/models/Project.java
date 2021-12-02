package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;

import java.util.ArrayList;
import java.util.List;

public class Project extends Entity
{
    private Id contractorId;
    private int department;
    private final List<Id> workersId;

    private Project(Id id, Id contractorId, int department, Date creationDate)
    {
        super(id, creationDate);

        this.contractorId = contractorId;
        this.department = department;
        this.workersId = new ArrayList<>();
    }

    public static Project of(Id id, Id contractorId, int department, Date creationDate)
    {
        return new Project(id, contractorId, department, creationDate);
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

    @Override
    public String toString()
    {
        StringBuilder project = new StringBuilder(
                super.toString() +
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
            this.workersId.forEach(workerId -> project.append(workerId.toString()));
        }

        return project.toString();
    }
}
