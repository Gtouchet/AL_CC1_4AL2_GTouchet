package al.cc2.gtouchet.application.services.dtos.worker;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class DeleteWorkerCommand implements Command
{
    public final Id id;

    public DeleteWorkerCommand(Id id)
    {
        this.id = id;
    }
}
