package esgi.al.gtouchet.cc2.application.projectServices.update;

import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

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
