package esgi.al.gtouchet.cc2.application.projectServices;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.application.projectServices.create.CreateProjectDto;
import esgi.al.gtouchet.cc2.application.projectServices.create.CreateProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.projectServices.delete.DeleteProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.projectServices.engageWorker.EngageWorkerDto;
import esgi.al.gtouchet.cc2.application.projectServices.engageWorker.EngageWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.projectServices.fireWorker.FireWorkerDto;
import esgi.al.gtouchet.cc2.application.projectServices.fireWorker.FireWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.projectServices.read.ReadAllProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.projectServices.read.ReadIdProjectServiceHandler;
import esgi.al.gtouchet.cc2.application.projectServices.update.UpdateProjectDto;
import esgi.al.gtouchet.cc2.application.projectServices.update.UpdateProjectServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.DataRepositoriesFactory;

import java.util.List;

public class ProjectServicesFactory
{
    private final DataRepositoriesFactory dataRepositoriesFactory;

    public ProjectServicesFactory(DataRepositoriesFactory dataRepositoriesFactory)
    {
        this.dataRepositoriesFactory = dataRepositoriesFactory;
    }

    public ServiceHandler<Project, CreateProjectDto> getCreateProjectHandler()
    {
        return new CreateProjectServiceHandler(
                this.dataRepositoriesFactory.createProjectRepository(),
                this.dataRepositoriesFactory.createContractorRepository()
        );
    }

    public ServiceHandler<List<Project>, Void> getReadAllProjectHandler()
    {
        return new ReadAllProjectServiceHandler(
                this.dataRepositoriesFactory.createProjectRepository()
        );
    }

    public ServiceHandler<Project, Id> getReadIdProjectHandle()
    {
        return new ReadIdProjectServiceHandler(
                this.dataRepositoriesFactory.createProjectRepository()
        );
    }

    public ServiceHandler<Project, UpdateProjectDto> getUpdateProjectHandler()
    {
        return new UpdateProjectServiceHandler(
                this.dataRepositoriesFactory.createProjectRepository(),
                this.dataRepositoriesFactory.createContractorRepository()
        );
    }

    public ServiceHandler<Boolean, Id> getDeleteProjectHandler()
    {
        return new DeleteProjectServiceHandler(
                this.dataRepositoriesFactory.createProjectRepository()
        );
    }

    public ServiceHandler<Project, EngageWorkerDto> getEngageProjectHandler()
    {
        return new EngageWorkerServiceHandler(
                this.dataRepositoriesFactory.createProjectRepository(),
                this.dataRepositoriesFactory.createWorkerRepository()
        );
    }

    public ServiceHandler<Project, FireWorkerDto> getFireProjectHandler()
    {
        return new FireWorkerServiceHandler(
                this.dataRepositoriesFactory.createProjectRepository(),
                this.dataRepositoriesFactory.createWorkerRepository()
        );
    }
}
