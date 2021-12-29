package al.cc2.gtouchet.domain.models.project;

import al.cc2.gtouchet.domain.models.Entity;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

import java.util.List;

public final class Project extends Entity
{
    private final EntityId contractorId;
    private final int department;
    private final List<EntityId> workersId;

    private Project(EntityId id, EntityId contractorId, int department, List<EntityId> workersId, Clock creationClock)
    {
        super(id, creationClock);

        this.contractorId = contractorId;
        this.department = department;
        this.workersId = workersId;
    }

    public static Project of(EntityId id, EntityId contractorId, int department, List<EntityId> workersId, Clock creationClock)
    {
        return new Project(id, contractorId, department, workersId, creationClock);
    }

    public EntityId getContractorId()
    {
        return this.contractorId;
    }

    public int getDepartment()
    {
        return this.department;
    }

    public List<EntityId> getWorkersId()
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
