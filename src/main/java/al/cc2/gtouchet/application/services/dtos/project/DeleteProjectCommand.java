package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class DeleteProjectCommand implements Command
{
    public final EntityId id;

    public DeleteProjectCommand(EntityId id)
    {
        this.id = id;
    }
}
