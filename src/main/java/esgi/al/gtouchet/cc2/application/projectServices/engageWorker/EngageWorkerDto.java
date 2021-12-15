package esgi.al.gtouchet.cc2.application.projectServices.engageWorker;

import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class EngageWorkerDto
{
    public final Id projectId;
    public final Id workerId;

    public EngageWorkerDto(
            Id projectId,
            Id workerId)
    {
        this.projectId = projectId;
        this.workerId = workerId;
    }
}
