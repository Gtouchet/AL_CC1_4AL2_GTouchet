package al.cc2.gtouchet.application.services.dtos.worker;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.models.user.WorkerService;
import al.cc2.gtouchet.domain.valueObjects.Password;

public final class CreateWorkerCommand implements Command
{
    public final String login;
    public final Password password;
    public final String name;
    public final WorkerService workerService;
    public final int department;

    public CreateWorkerCommand(
            String login,
            Password password,
            String name,
            WorkerService workerService,
            int department)
    {
        this.login = login;
        this.password = password;
        this.name = name;
        this.workerService = workerService;
        this.department = department;
    }
}
