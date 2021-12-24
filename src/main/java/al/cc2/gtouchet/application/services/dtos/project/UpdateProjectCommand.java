package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class UpdateProjectCommand implements Command
{
    public final Id id;
    public final Id contractorId;
    public final int department;

    public UpdateProjectCommand(
            Id id,
            Id contractorId,
            int department)
    {
        this.id = id;
        this.contractorId = contractorId;
        this.department = department;
    }
}
