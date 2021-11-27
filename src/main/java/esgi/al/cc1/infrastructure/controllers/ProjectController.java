package esgi.al.cc1.infrastructure.controllers;

import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.stream.Stream;

public class ProjectController implements Controller<Project>
{
    private final Repository<Project> projectRepository;

    public ProjectController(Repository<Project> projectRepository)
    {
        this.projectRepository = projectRepository;
    }

    @Override
    public void create(String[] values) throws FailedToCreate
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
    public void update(Project element) throws ElementNotFound, FailedToUpdate
    {

    }

    @Override
    public void remove(String id) throws ElementNotFound
    {

    }
}
