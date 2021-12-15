package esgi.al.gtouchet.cc2.application.workerServices;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.application.workerServices.create.CreateWorkerDto;
import esgi.al.gtouchet.cc2.application.workerServices.create.CreateWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.workerServices.delete.DeleteWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.workerServices.read.ReadAllWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.workerServices.read.ReadIdWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.workerServices.update.UpdateWorkerDto;
import esgi.al.gtouchet.cc2.application.workerServices.update.UpdateWorkerServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.DataRepositoriesFactory;

import java.util.List;

public class WorkerServicesFactory
{
    private final DataRepositoriesFactory dataRepositoriesFactory;

    public WorkerServicesFactory(DataRepositoriesFactory dataRepositoriesFactory)
    {
        this.dataRepositoriesFactory = dataRepositoriesFactory;
    }

    public ServiceHandler<Worker, CreateWorkerDto> getCreateWorkerHandler()
    {
        return new CreateWorkerServiceHandler(
                this.dataRepositoriesFactory.createWorkerRepository(),
                this.dataRepositoriesFactory.createContractorRepository(),
                new PasswordValidator()
        );
    }

    public ServiceHandler<List<Worker>, Void> getReadAllWorkerHandler()
    {
        return new ReadAllWorkerServiceHandler(
                this.dataRepositoriesFactory.createWorkerRepository()
        );
    }

    public ServiceHandler<Worker, Id> getReadIdWorkerHandler()
    {
        return new ReadIdWorkerServiceHandler(
                this.dataRepositoriesFactory.createWorkerRepository()
        );
    }

    public ServiceHandler<Worker, UpdateWorkerDto> getUpdateWorkerHandler()
    {
        return new UpdateWorkerServiceHandler(
                this.dataRepositoriesFactory.createWorkerRepository(),
                new PasswordValidator()
        );
    }

    public ServiceHandler<Boolean, Id> getDeleteWorkerHandler()
    {
        return new DeleteWorkerServiceHandler(
                this.dataRepositoriesFactory.createWorkerRepository(),
                this.dataRepositoriesFactory.createProjectRepository()
        );
    }
}
