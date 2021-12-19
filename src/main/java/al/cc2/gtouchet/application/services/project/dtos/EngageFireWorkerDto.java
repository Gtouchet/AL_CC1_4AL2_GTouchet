package al.cc2.gtouchet.application.services.project.dtos;

import al.cc2.gtouchet.domain.valueObjects.Id;

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
