package al.cc2.gtouchet.domain.builders;

import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ProjectBuilder implements Builder<Project>
{
    private final EntityId id;
    private EntityId contractorId;
    private int department;
    private List<EntityId> workersId;
    private final Clock creationClock;

    private ProjectBuilder(EntityId id, Clock creationClock)
    {
        this.id = id;
        this.creationClock = creationClock;
    }

    @Override
    public Project build() throws NullPointerException
    {
        return Project.of(
                Objects.requireNonNull(this.id),
                Objects.requireNonNull(this.contractorId),
                this.department,
                Objects.requireNonNullElse(this.workersId, new ArrayList<>()),
                Objects.requireNonNull(this.creationClock)
        );
    }

    public static ProjectBuilder init(EntityId id, Clock creationClock)
    {
        return new ProjectBuilder(id, creationClock);
    }

    public static ProjectBuilder init(Project project)
    {
        ProjectBuilder builder = new ProjectBuilder(
                project.getId(),
                project.getCreationDate()
        );

        builder.contractorId = project.getContractorId();
        builder.department = project.getDepartment();
        builder.workersId = project.getWorkersId();

        return builder;
    }

    public ProjectBuilder setContractorId(EntityId contractorId)
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

    public ProjectBuilder setWorkersId(List<EntityId> workersId)
    {
        ProjectBuilder builder = this;
        builder.workersId = workersId;
        return builder;
    }
}
