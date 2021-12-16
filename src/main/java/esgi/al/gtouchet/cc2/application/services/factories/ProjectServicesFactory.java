package esgi.al.gtouchet.cc2.application.services.factories;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.dtos.CreateProjectDto;
import esgi.al.gtouchet.cc2.application.services.project.CreateProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.DeleteProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.dtos.EngageFireWorkerDto;
import esgi.al.gtouchet.cc2.application.services.project.EngageWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.FireWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.ReadAllProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.ReadIdProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.dtos.UpdateProjectDto;
import esgi.al.gtouchet.cc2.application.services.project.UpdateProjectServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;

import java.util.List;

public class ProjectServicesFactory
{
    private final RepositoriesFactory repositoriesFactory;

    public ProjectServicesFactory(RepositoriesFactory repositoriesFactory)
    {
        this.repositoriesFactory = repositoriesFactory;
    }

    public ServiceHandler<Project, CreateProjectDto> getCreateProjectHandler()
    {
        return new CreateProjectServiceHandler(
                this.repositoriesFactory.createProjectRepository(),
                this.repositoriesFactory.createContractorRepository()
        );
    }

    public ServiceHandler<List<Project>, Void> getReadAllProjectHandler()
    {
        return new ReadAllProjectServiceHandler(
                this.repositoriesFactory.createProjectRepository()
        );
    }

    public ServiceHandler<Project, Id> getReadIdProjectHandle()
    {
        return new ReadIdProjectServiceHandler(
                this.repositoriesFactory.createProjectRepository()
        );
    }

    public ServiceHandler<Project, UpdateProjectDto> getUpdateProjectHandler()
    {
        return new UpdateProjectServiceHandler(
                this.repositoriesFactory.createProjectRepository(),
                this.repositoriesFactory.createContractorRepository()
        );
    }

    public ServiceHandler<Boolean, Id> getDeleteProjectHandler()
    {
        return new DeleteProjectServiceHandler(
                this.repositoriesFactory.createProjectRepository()
        );
    }

    public ServiceHandler<Project, EngageFireWorkerDto> getEngageProjectHandler()
    {
        return new EngageWorkerServiceHandler(
                this.repositoriesFactory.createProjectRepository(),
                this.repositoriesFactory.createWorkerRepository()
        );
    }

    public ServiceHandler<Project, EngageFireWorkerDto> getFireProjectHandler()
    {
        return new FireWorkerServiceHandler(
                this.repositoriesFactory.createProjectRepository(),
                this.repositoriesFactory.createWorkerRepository()
        );
    }
}
