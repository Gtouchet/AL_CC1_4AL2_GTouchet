package al.cc2.gtouchet.application.services.project.dtos;

import al.cc2.gtouchet.domain.valueObjects.Id;

public class CreateProjectDto
{
    public final Id contractorId;
    public final int department;

    public CreateProjectDto(
            Id contractorId,
            int department)
    {
        this.contractorId = contractorId;
        this.department = department;
    }
}
