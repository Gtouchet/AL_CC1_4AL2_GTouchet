package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
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

    private final Repository<Worker> workerRepository;

    public ProjectRepository(JsonDataAccessor<Project> jsonDataAccessor, Repository<Worker> workerRepository)
    {
        this.jsonDataAccessor = jsonDataAccessor;
        this.projects = this.getDataFromJsonFile();

        this.workerRepository = workerRepository;
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
    public void create(Project project) throws FailedToCreate
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
    public Project read(String id) throws ElementNotFound
    {
        return this.findById(id);
    }

    @Override
    public void update(String id, Project project) throws ElementNotFound, FailedToUpdate
    {
        Project registeredProject = this.findById(id);

        this.projects.remove(registeredProject);
        registeredProject.setDepartment(project.getDepartment());
        this.projects.add(registeredProject);

        this.writeJsonFile();
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.projects.remove(this.findById(id));

        this.writeJsonFile();
    }

    @Override
    public void validatePayment(String id) throws ElementNotFound
    {
        // Do nothing
    }

    @Override
    public void addWorker(String projectId, String workerId) throws ElementNotFound, FailedToUpdate
    {
        Project project = this.findById(projectId);
        Worker worker = this.findWorkerById(workerId);

        boolean alreadyWorkingOn = project.getWorkersId().stream()
                .anyMatch(wId -> wId.toString().equals(workerId));
        if (alreadyWorkingOn)
        {
            throw new FailedToUpdate(); // todo: exception message
        }

        project.addWorker(worker);

        this.writeJsonFile();
    }

    @Override
    public void removeWorker(String projectId, String workerId) throws ElementNotFound, FailedToUpdate
    {
        Project project = this.findById(projectId);
        Worker worker = this.findWorkerById(workerId);

        boolean workingOn = project.getWorkersId().stream()
                .anyMatch(wId -> wId.toString().equals(workerId));
        if (!workingOn)
        {
            throw new FailedToUpdate(); // todo: exception message
        }

        project.removeWorker(worker);

        this.writeJsonFile();
    }

    private Project findById(String id) throws ElementNotFound
    {
        Project registeredProject = this.projects.stream()
                .filter(p -> p.getId().toString().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredProject == null)
        {
            throw new ElementNotFound(Project.class, id);
        }

        return registeredProject;
    }

    private Worker findWorkerById(String id) throws ElementNotFound
    {
        Worker registeredWorker = this.workerRepository.read()
                .filter(w -> w.getId().toString().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredWorker == null)
        {
            throw new ElementNotFound(Worker.class, id);
        }

        return registeredWorker;
    }
}
