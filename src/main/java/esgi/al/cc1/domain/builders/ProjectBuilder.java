package esgi.al.cc1.domain.builders;

import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectBuilder implements Builder<Project>
{
    private final Id id;
    private Id contractorId;
    private int department;
    private List<Id> workersId;
    private final Date creationDate;

    private ProjectBuilder(Id id, Date creationDate)
    {
        this.id = id;
        this.creationDate = creationDate;
    }

    @Override
    public Project build()
    {
        return Project.of(
                this.id,
                Objects.requireNonNull(this.contractorId),
                this.department,
                Objects.requireNonNullElse(this.workersId, new ArrayList<>()),
                this.creationDate
        );
    }

    public static ProjectBuilder init(Id id, Date creationDate)
    {
        return new ProjectBuilder(
                Objects.requireNonNull(id),
                Objects.requireNonNull(creationDate)
        );
    }

    public static ProjectBuilder init(Project project)
    {
        ProjectBuilder builder = new ProjectBuilder(
                Objects.requireNonNull(project.getId()),
                Objects.requireNonNull(project.getCreationDate())
        );

        builder.contractorId = project.getContractorId();
        builder.department = project.getDepartment();
        builder.workersId = project.getWorkersId();

        return builder;
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
