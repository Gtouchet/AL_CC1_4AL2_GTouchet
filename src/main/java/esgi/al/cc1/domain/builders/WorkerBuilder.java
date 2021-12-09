package esgi.al.cc1.domain.builders;

import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

import java.util.Objects;

public class WorkerBuilder implements Builder<Worker>
{
    private final Id id;
    private final String login;
    private Password password;
    private String name;
    private Service service;
    private int department;
    private final Date creationDate;

    private WorkerBuilder(Id id, String login, Date creationDate)
    {
        this.id = id;
        this.login = login;
        this.creationDate = creationDate;
    }

    @Override
    public Worker build() throws NullPointerException
    {
        return Worker.of(
                Objects.requireNonNull(this.id),
                Objects.requireNonNull(this.login),
                Objects.requireNonNull(this.password),
                Objects.requireNonNull(this.name),
                Objects.requireNonNull(this.service),
                this.department,
                Objects.requireNonNull(this.creationDate)
        );
    }

    public static WorkerBuilder init(Id id, String login, Date creationDate)
    {
        return new WorkerBuilder(id, login, creationDate);
    }

    public static WorkerBuilder init(Worker worker)
    {
        WorkerBuilder builder = new WorkerBuilder(
                worker.getId(),
                worker.getLogin(),
                worker.getCreationDate()
        );

        builder.password = worker.getPassword();
        builder.name = worker.getName();
        builder.service = worker.getService();
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

    public WorkerBuilder setService(Service service)
    {
        WorkerBuilder builder = this;
        builder.service = service;
        return builder;
    }

    public WorkerBuilder setDepartment(int department)
    {
        WorkerBuilder builder = this;
        builder.department = department;
        return builder;
    }
}
