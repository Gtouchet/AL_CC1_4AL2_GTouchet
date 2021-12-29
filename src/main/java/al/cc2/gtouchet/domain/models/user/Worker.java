package al.cc2.gtouchet.domain.models.user;

import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class Worker extends User
{
    private final WorkerService workerService;
    private final int department;

    private Worker(Id id, Credentials credentials, String name, WorkerService workerService, int department, Date creationDate)
    {
        super(id, credentials, name, creationDate);

        this.workerService = workerService;
        this.department = department;
    }

    public static Worker of(Id id, Credentials credentials, String name, WorkerService workerService, int department, Date creationDate)
    {
        return new Worker(id, credentials, name, workerService, department, creationDate);
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
