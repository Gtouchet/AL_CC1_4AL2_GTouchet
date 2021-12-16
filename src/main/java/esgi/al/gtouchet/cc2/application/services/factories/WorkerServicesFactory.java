package esgi.al.gtouchet.cc2.application.services.factories;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.dtos.CreateWorkerDto;
import esgi.al.gtouchet.cc2.application.services.worker.CreateWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.DeleteWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.ReadAllWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.ReadIdWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.dtos.UpdateWorkerDto;
import esgi.al.gtouchet.cc2.application.services.worker.UpdateWorkerServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;

import java.util.List;

public class WorkerServicesFactory
{
    private final RepositoriesFactory repositoriesFactory;

    public WorkerServicesFactory(RepositoriesFactory repositoriesFactory)
    {
        this.repositoriesFactory = repositoriesFactory;
    }

    public ServiceHandler<Worker, CreateWorkerDto> getCreateWorkerHandler()
    {
        return new CreateWorkerServiceHandler(
                this.repositoriesFactory.createWorkerRepository(),
                this.repositoriesFactory.createContractorRepository(),
                new PasswordValidator()
        );
    }

    public ServiceHandler<List<Worker>, Void> getReadAllWorkerHandler()
    {
        return new ReadAllWorkerServiceHandler(
                this.repositoriesFactory.createWorkerRepository()
        );
    }

    public ServiceHandler<Worker, Id> getReadIdWorkerHandler()
    {
        return new ReadIdWorkerServiceHandler(
                this.repositoriesFactory.createWorkerRepository()
        );
    }

    public ServiceHandler<Worker, UpdateWorkerDto> getUpdateWorkerHandler()
    {
        return new UpdateWorkerServiceHandler(
                this.repositoriesFactory.createWorkerRepository(),
                new PasswordValidator()
        );
    }

    public ServiceHandler<Boolean, Id> getDeleteWorkerHandler()
    {
        return new DeleteWorkerServiceHandler(
                this.repositoriesFactory.createWorkerRepository(),
                this.repositoriesFactory.createProjectRepository()
        );
    }
}
