package al.cc2.gtouchet.application.services.project.dtos;

import al.cc2.gtouchet.domain.valueObjects.Id;

public class UpdateProjectDto
{
    public final Id id;
    public final Id contractorId;
    public final int department;

    public UpdateProjectDto(
            Id id,
            Id contractorId,
            int department)
    {
        this.id = id;
        this.contractorId = contractorId;
        this.department = department;
    }
}
