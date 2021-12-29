package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class UpdateProjectCommand implements Command
{
    public final EntityId id;
    public final EntityId contractorId;
    public final int department;

    public UpdateProjectCommand(
            EntityId id,
            EntityId contractorId,
            int department)
    {
        this.id = id;
        this.contractorId = contractorId;
        this.department = department;
    }
}
