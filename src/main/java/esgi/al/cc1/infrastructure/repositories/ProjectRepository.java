package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonDataAccessor;

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

    private List<Project> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonDataAccessor.getDataFromFile()));
    }

    private void writeJsonFile()
    {
        this.jsonDataAccessor.writeInFile(this.projects);
    }

    @Override
    public void create(Project element) throws FailedToCreate
    {

    }

    @Override
    public Stream<Project> read()
    {
        return null;
    }

    @Override
    public Project read(String id) throws ElementNotFound
    {
        return null;
    }

    @Override
    public void update(String id, Project element) throws ElementNotFound, FailedToUpdate
    {

    }

    @Override
    public void remove(String id) throws ElementNotFound
    {

    }

    @Override
    public void validatePayment(String id) throws ElementNotFound
    {
        // Do nothing
    }
}
