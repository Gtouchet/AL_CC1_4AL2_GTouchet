package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class EngageFireWorkerCommand implements Command
{
    public final Id projectId;
    public final Id workerId;

    public EngageFireWorkerCommand(
            Id projectId,
            Id workerId)
    {
        this.projectId = projectId;
        this.workerId = workerId;
    }
}
