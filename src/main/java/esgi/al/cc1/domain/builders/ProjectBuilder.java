package esgi.al.cc1.domain.builders;

import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;

import java.util.List;
import java.util.Objects;

public class ProjectBuilder implements Builder<Project>
{
    private final Id id;
    private Id contractorId;
    private int department;
    private List<Id> workersId;
    private final Date creationDate;

    private ProjectBuilder(Project project)
    {
        this.id = Objects.requireNonNull(project.getId());
        this.contractorId = Objects.requireNonNull(project.getContractorId());
        this.department = project.getDepartment();
        this.workersId = Objects.requireNonNull(project.getWorkersId());
        this.creationDate = Objects.requireNonNull(project.getCreationDate());
    }

    public static ProjectBuilder init(Project project)
    {
        return new ProjectBuilder(project);
    }

    @Override
    public Project build()
    {
        return Project.of(
                this.id,
                this.contractorId,
                this.department,
                this.workersId,
                this.creationDate
        );
    }

    public ProjectBuilder setContractorId(Id contractorId)
    {
        ProjectBuilder builder = this;
        builder.contractorId = contractorId;
        return builder;
    }

    public ProjectBuilder setDepartment(int department)
    {
        ProjectBuilder builder = this;
        builder.department = department;
        return builder;
    }

    public ProjectBuilder setWorkersId(List<Id> workersId)
    {
        ProjectBuilder builder = this;
        builder.workersId = workersId;
        return builder;
    }
}
