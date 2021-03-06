package al.cc2.gtouchet.application.services.dtos.worker;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.models.user.WorkerService;
import al.cc2.gtouchet.domain.valueObjects.EntityId;
import al.cc2.gtouchet.domain.valueObjects.Password;

public final class UpdateWorkerCommand implements Command
{
    public final EntityId id;
    public final Password password;
    public final String name;
    public final WorkerService workerService;
    public final int department;

    public UpdateWorkerCommand(
            EntityId id,
            Password password,
            String name,
            WorkerService workerService,
            int department)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.workerService = workerService;
        this.department = department;
    }
}
