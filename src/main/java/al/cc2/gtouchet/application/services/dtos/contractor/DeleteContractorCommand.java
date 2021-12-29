package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class DeleteContractorCommand implements Command
{
    public final EntityId id;

    public DeleteContractorCommand(EntityId id)
    {
        this.id = id;
    }
}
