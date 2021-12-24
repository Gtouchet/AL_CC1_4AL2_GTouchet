package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class DeleteProjectCommand implements Command
{
    public final Id id;

    public DeleteProjectCommand(Id id)
    {
        this.id = id;
    }
}
