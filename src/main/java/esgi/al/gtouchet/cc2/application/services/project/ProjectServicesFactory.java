package esgi.al.gtouchet.cc2.application.services.project;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.create.CreateProjectDto;
import esgi.al.gtouchet.cc2.application.services.project.create.CreateProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.delete.DeleteProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.engageWorker.EngageWorkerDto;
import esgi.al.gtouchet.cc2.application.services.project.engageWorker.EngageWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.fireWorker.FireWorkerDto;
import esgi.al.gtouchet.cc2.application.services.project.fireWorker.FireWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.read.ReadAllProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.read.ReadIdProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.update.UpdateProjectDto;
import esgi.al.gtouchet.cc2.application.services.project.update.UpdateProjectServiceHandler;
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

    public ServiceHandler<Project, EngageWorkerDto> getEngageProjectHandler()
    {
        return new EngageWorkerServiceHandler(
                this.repositoriesFactory.createProjectRepository(),
                this.repositoriesFactory.createWorkerRepository()
        );
    }

    public ServiceHandler<Project, FireWorkerDto> getFireProjectHandler()
    {
        return new FireWorkerServiceHandler(
                this.repositoriesFactory.createProjectRepository(),
                this.repositoriesFactory.createWorkerRepository()
        );
    }
}
