package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class CreateProjectCommand implements Command
{
    public final Id contractorId;
    public final int department;

    public CreateProjectCommand(
            Id contractorId,
            int department)
    {
        this.contractorId = contractorId;
        this.department = department;
    }
}
