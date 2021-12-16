package esgi.al.gtouchet.cc2.application.services.project.dtos;

import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class EngageFireWorkerDto
{
    public final Id projectId;
    public final Id workerId;

    public EngageFireWorkerDto(
            Id projectId,
            Id workerId)
    {
        this.projectId = projectId;
        this.workerId = workerId;
    }
}
