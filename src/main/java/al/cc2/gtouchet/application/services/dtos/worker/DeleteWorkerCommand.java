package al.cc2.gtouchet.application.services.dtos.worker;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class DeleteWorkerCommand implements Command
{
    public final EntityId id;

    public DeleteWorkerCommand(EntityId id)
    {
        this.id = id;
    }
}
