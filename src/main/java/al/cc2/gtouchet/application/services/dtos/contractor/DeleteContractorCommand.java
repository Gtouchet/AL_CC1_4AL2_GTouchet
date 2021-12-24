package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class DeleteContractorCommand implements Command
{
    public final Id id;

    public DeleteContractorCommand(Id id)
    {
        this.id = id;
    }
}
