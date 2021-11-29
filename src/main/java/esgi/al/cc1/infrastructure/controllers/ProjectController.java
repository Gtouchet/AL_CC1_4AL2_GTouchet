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
    public void create(String[] values)
    {
        try {
            int department = Integer.parseInt(values[1]);

            this.projectRepository.create(Project.of(department));

        } catch (NumberFormatException e) {
            System.out.println("Error: impossible to parse [" + values[1] + "] as a department number");
        } catch (FailedToCreate e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Stream<Project> read()
    {
        return this.projectRepository.read();
    }

    @Override
    public Project read(String id)
    {
        try {
            return this.projectRepository.read(id);
        } catch (ElementNotFound e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(String[] values)
    {
        try {
            int department = Integer.parseInt(values[2]);

            this.projectRepository.update(
                    values[1].toLowerCase(),
                    Project.of(department)
            );
        } catch (NumberFormatException e) {
            System.out.println("Error: impossible to parse [" + values[2] + "] as a department number");
        } catch (ElementNotFound | FailedToUpdate e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(String id)
    {
        try {
            this.projectRepository.remove(id);
        } catch (ElementNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void validatePayment(String id)
    {
        // Do nothing
    }

    @Override
    public void addWorker(String[] values)
    {
        try {
            this.projectRepository.addWorker(
                    values[1].toLowerCase(),
                    values[2].toLowerCase()
            );
        } catch (ElementNotFound | FailedToUpdate e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeWorker(String[] values)
    {
        try {
            this.projectRepository.removeWorker(
                    values[1].toLowerCase(),
                    values[2].toLowerCase()
            );
        } catch (ElementNotFound | FailedToUpdate e) {
            System.out.println(e.getMessage());
        }
    }
}
