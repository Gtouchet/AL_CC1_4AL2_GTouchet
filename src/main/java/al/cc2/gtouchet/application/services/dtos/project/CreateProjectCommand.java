package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class CreateProjectCommand implements Command
{
    public final EntityId contractorId;
    public final int department;

    public CreateProjectCommand(
            EntityId contractorId,
            int department)
    {
        this.contractorId = contractorId;
        this.department = department;
    }
}
