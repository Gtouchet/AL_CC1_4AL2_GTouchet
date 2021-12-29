package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class EngageFireWorkerCommand implements Command
{
    public final EntityId projectId;
    public final EntityId workerId;

    public EngageFireWorkerCommand(
            EntityId projectId,
            EntityId workerId)
    {
        this.projectId = projectId;
        this.workerId = workerId;
    }
}
