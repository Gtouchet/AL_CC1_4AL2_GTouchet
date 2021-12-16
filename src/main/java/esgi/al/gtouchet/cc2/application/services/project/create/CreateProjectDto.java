package esgi.al.gtouchet.cc2.application.services.project.create;

import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

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
