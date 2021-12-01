package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.utilitaries.JsonDataAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ProjectRepository implements Repository<Project>
{
    private final List<Project> projects;
    private final JsonDataAccessor<Project> jsonDataAccessor;

    public ProjectRepository(JsonDataAccessor<Project> jsonDataAccessor)
    {
        this.jsonDataAccessor = jsonDataAccessor;
        this.projects = this.getDataFromJsonFile();
    }

    @Override
    public void create(Project project) throws FailedToCreateException
    {
        this.projects.add(project);
        this.writeJsonFile();
    }

    @Override
    public Stream<Project> read()
    {
        return this.projects.stream();
    }

    @Override
    public Project read(Id id) throws ElementNotFoundException
    {
        return this.findById(id);
    }

    @Override
    public void update(Id id, Project project) throws ElementNotFoundException, FailedToUpdateException
    {
        Project registeredProject = this.findById(id);

        this.projects.remove(registeredProject);

        registeredProject.setContractorId(project.getId());
        registeredProject.setDepartment(project.getDepartment());

        this.projects.add(registeredProject);
        this.writeJsonFile();
    }

    @Override
    public void remove(Id id) throws ElementNotFoundException
    {
        this.projects.remove(this.findById(id));
        this.writeJsonFile();
    }

    @Override
    public boolean exists(Id id)
    {
        return this.projects.stream().anyMatch(project -> project.getId().equals(id));
    }

    private List<Project> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonDataAccessor.getDataFromFile()));
    }

    private void writeJsonFile()
    {
        this.jsonDataAccessor.writeInFile(this.projects);
    }

    private Project findById(Id id) throws ElementNotFoundException
    {
        Project registeredProject = this.projects.stream()
                .filter(project -> project.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredProject == null)
        {
            throw new ElementNotFoundException(Project.class, id.toString());
        }

        return registeredProject;
    }
}
