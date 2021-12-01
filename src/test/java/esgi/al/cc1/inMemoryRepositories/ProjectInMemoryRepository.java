package esgi.al.cc1.inMemoryRepositories;

import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProjectInMemoryRepository implements Repository<Project>
{
    private final List<Project> projects;

    public ProjectInMemoryRepository()
    {
        this.projects = new ArrayList<>();
    }

    @Override
    public void create(Project project)
    {
        this.projects.add(project);
    }

    @Override
    public Stream<Project> read()
    {
        return this.projects.stream();
    }

    @Override
    public Project read(Id id)
    {
        return this.findById(id);
    }

    @Override
    public void update(Id id, Project project)
    {
        Project registeredProject = this.findById(id);

        if (registeredProject == null)
        {
            return;
        }

        this.projects.remove(registeredProject);

        registeredProject.setContractorId(project.getContractorId());
        registeredProject.setDepartment(project.getDepartment());

        this.projects.add(registeredProject);
    }

    @Override
    public void remove(Id id)
    {
        this.projects.remove(this.findById(id));
    }

    @Override
    public boolean exists(Id id)
    {
        return this.projects.stream().anyMatch(project -> project.getId().equals(id));
    }

    private Project findById(Id id)
    {
        return this.projects.stream()
                .filter(project -> project.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
