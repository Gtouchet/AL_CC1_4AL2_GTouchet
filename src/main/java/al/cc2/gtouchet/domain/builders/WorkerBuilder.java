package al.cc2.gtouchet.domain.builders;

import al.cc2.gtouchet.domain.models.user.Credentials;
import al.cc2.gtouchet.domain.models.user.WorkerService;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;
import al.cc2.gtouchet.domain.valueObjects.Password;

import java.util.Objects;

public final class WorkerBuilder implements Builder<Worker>
{
    private final EntityId id;
    private final String login;
    private Password password;
    private String name;
    private WorkerService workerService;
    private int department;
    private final Clock creationClock;

    private WorkerBuilder(EntityId id, String login, Clock creationClock)
    {
        this.id = id;
        this.login = login;
        this.creationClock = creationClock;
    }

    @Override
    public Worker build() throws NullPointerException
    {
        return Worker.of(
                Objects.requireNonNull(this.id),
                new Credentials(
                        Objects.requireNonNull(this.login),
                        Objects.requireNonNull(this.password)
                ),
                Objects.requireNonNull(this.name),
                Objects.requireNonNull(this.workerService),
                this.department,
                Objects.requireNonNull(this.creationClock)
        );
    }

    public static WorkerBuilder init(EntityId id, String login, Clock creationClock)
    {
        return new WorkerBuilder(id, login, creationClock);
    }

    public static WorkerBuilder init(Worker worker)
    {
        WorkerBuilder builder = new WorkerBuilder(
                worker.getId(),
                worker.getCredentials().getLogin(),
                worker.getCreationDate()
        );

        builder.password = worker.getCredentials().getPassword();
        builder.name = worker.getName();
        builder.workerService = worker.getService();
        builder.department = worker.getDepartment();

        return builder;
    }

    public WorkerBuilder setPassword(Password password)
    {
        WorkerBuilder builder = this;
        builder.password = password;
        return builder;
    }

    public WorkerBuilder setName(String name)
    {
        WorkerBuilder builder = this;
        builder.name = name;
        return builder;
    }

    public WorkerBuilder setService(WorkerService workerService)
    {
        WorkerBuilder builder = this;
        builder.workerService = workerService;
        return builder;
    }

    public WorkerBuilder setDepartment(int department)
    {
        WorkerBuilder builder = this;
        builder.department = department;
        return builder;
    }
}
