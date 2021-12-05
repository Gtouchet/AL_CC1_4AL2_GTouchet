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

    private WorkerBuilder(Worker worker)
    {
        this.id = Objects.requireNonNull(worker.getId());
        this.login = Objects.requireNonNull(worker.getLogin());
        this.password = Objects.requireNonNull(worker.getPassword());
        this.name = Objects.requireNonNull(worker.getName());
        this.service = Objects.requireNonNull(worker.getService());
        this.department = worker.getDepartment();
        this.creationDate = Objects.requireNonNull(worker.getCreationDate());
    }

    public static WorkerBuilder init(Worker worker)
    {
        return new WorkerBuilder(worker);
    }

    @Override
    public Worker build()
    {
        return Worker.of(
                this.id,
                this.login,
                this.password,
                this.name,
                this.service,
                this.department,
                this.creationDate
        );
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
