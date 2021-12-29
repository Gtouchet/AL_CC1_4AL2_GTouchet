package al.cc2.gtouchet.domain.models.user;

import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class Worker extends User
{
    private final WorkerService workerService;
    private final int department;

    private Worker(EntityId id, Credentials credentials, String name, WorkerService workerService, int department, Clock creationClock)
    {
        super(id, credentials, name, creationClock);

        this.workerService = workerService;
        this.department = department;
    }

    public static Worker of(EntityId id, Credentials credentials, String name, WorkerService workerService, int department, Clock creationClock)
    {
        return new Worker(id, credentials, name, workerService, department, creationClock);
    }

    public WorkerService getService()
    {
        return this.workerService;
    }

    public int getDepartment()
    {
        return this.department;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "\nService: " + this.workerService +
                "\nDepartment: " + this.department;
    }
}
