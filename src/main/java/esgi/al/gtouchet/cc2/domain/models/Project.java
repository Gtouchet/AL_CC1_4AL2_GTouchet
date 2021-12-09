package esgi.al.gtouchet.cc2.domain.models;

import esgi.al.gtouchet.cc2.domain.valueObjects.Date;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

import java.util.List;

public class Project extends Entity
{
    private final Id contractorId;
    private final int department;
    private final List<Id> workersId;

    private Project(Id id, Id contractorId, int department, List<Id> workersId, Date creationDate)
    {
        super(id, creationDate);

        this.contractorId = contractorId;
        this.department = department;
        this.workersId = workersId;
    }

    public static Project of(Id id, Id contractorId, int department, List<Id> workersId, Date creationDate)
    {
        return new Project(id, contractorId, department, workersId, creationDate);
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
