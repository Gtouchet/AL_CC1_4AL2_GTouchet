package esgi.al.gtouchet.cc2.application.services.project.fireWorker;

import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class FireWorkerDto
{
    public final Id projectId;
    public final Id workerId;

    public FireWorkerDto(
            Id projectId,
            Id workerId)
    {
        this.projectId = projectId;
        this.workerId = workerId;
    }
}
