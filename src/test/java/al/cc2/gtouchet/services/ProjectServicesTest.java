package al.cc2.gtouchet.services;

import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.application.services.dtos.contractor.CreateContractorCommand;
import al.cc2.gtouchet.application.services.dtos.project.*;
import al.cc2.gtouchet.application.services.dtos.worker.CreateWorkerCommand;
import al.cc2.gtouchet.application.services.dtos.worker.DeleteWorkerCommand;
import al.cc2.gtouchet.application.services.handlers.contractor.CreateContractorCommandHandler;
import al.cc2.gtouchet.application.services.handlers.project.*;
import al.cc2.gtouchet.application.services.handlers.worker.CreateWorkerCommandHandler;
import al.cc2.gtouchet.application.services.handlers.worker.DeleteWorkerCommandHandler;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.payment.PaymentMethod;
import al.cc2.gtouchet.domain.models.user.WorkerService;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.domain.valueObjects.Password;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.factories.MemoryRepositoriesRetainer;
import al.cc2.gtouchet.infrastructure.repositories.factories.RepositoriesFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectServicesTest
{
    private RepositoriesFactory repositoriesFactory;
    private HandlersContainer handlersContainer;

    private Contractor contractor;
    private Worker worker;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();
        this.handlersContainer = HandlersContainer.initialize(
                this.repositoriesFactory,
                new PasswordValidator(),
                new PaymentMethodValidatorApi()
        );

        this.contractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                "GTouchet1",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));

        this.worker = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
                "GTouchet2",
                Password.of("ABcd1234!"),
                "Guillaume",
                WorkerService.BUILDER,
                91
        ));
    }

    @Test
    public void createProject()
    {
        long projectRepoSize = this.repositoriesFactory.createProjectRepository().read().count();

        assertEquals(0, projectRepoSize);

        Project project = (Project) this.handlersContainer.getCommandHandler(CreateProjectCommandHandler.class).handle(new CreateProjectCommand(
                this.contractor.getId(),
                75
        ));

        projectRepoSize = this.repositoriesFactory.createProjectRepository().read().count();

        assertEquals(1, projectRepoSize);
        assertTrue(this.repositoriesFactory.createProjectRepository().exists(project.getId()));
    }

    @Test
    public void deleteProject()
    {
        Project project = (Project) this.handlersContainer.getCommandHandler(CreateProjectCommandHandler.class).handle(new CreateProjectCommand(
                this.contractor.getId(),
                75
        ));

        assertTrue((boolean) this.handlersContainer.getCommandHandler(DeleteProjectCommandHandler.class).handle(new DeleteProjectCommand(
                project.getId()
        )));

        long projectRepoSize = this.repositoriesFactory.createProjectRepository().read().count();

        assertEquals(0, projectRepoSize);
        assertFalse(this.repositoriesFactory.createProjectRepository().exists(project.getId()));
    }

    @Test
    public void updateProject()
    {
        Project originalProject = (Project) this.handlersContainer.getCommandHandler(CreateProjectCommandHandler.class).handle(new CreateProjectCommand(
                this.contractor.getId(),
                75
        ));

        Contractor newContractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                "GTouchet3",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));
        int newDepartment = 91;

        Project updatedProject = (Project) this.handlersContainer.getCommandHandler(UpdateProjectCommandHandler.class).handle(new UpdateProjectCommand(
                originalProject.getId(),
                newContractor.getId(),
                newDepartment
        ));

        assertNotSame(originalProject, updatedProject);
        assertEquals(originalProject.getId(), updatedProject.getId());
        assertEquals(updatedProject.getContractorId(), newContractor.getId());
        assertEquals(updatedProject.getDepartment(), newDepartment);
    }

    @Test
    public void engageWorker()
    {
        Project project = (Project) this.handlersContainer.getCommandHandler(CreateProjectCommandHandler.class).handle(new CreateProjectCommand(
                this.contractor.getId(),
                75
        ));

        project = (Project) this.handlersContainer.getCommandHandler(EngageWorkerCommandHandler.class).handle(new EngageFireWorkerCommand(
                project.getId(),
                this.worker.getId()
        ));

        assertEquals(1, project.getWorkersId().size());
        assertTrue(project.getWorkersId().contains(this.worker.getId()));
    }

    @Test
    public void fireWorker()
    {
        Project project = (Project) this.handlersContainer.getCommandHandler(CreateProjectCommandHandler.class).handle(new CreateProjectCommand(
                this.contractor.getId(),
                75
        ));

        project = (Project) this.handlersContainer.getCommandHandler(EngageWorkerCommandHandler.class).handle(new EngageFireWorkerCommand(
                project.getId(),
                this.worker.getId()
        ));

        assertEquals(1, project.getWorkersId().size());
        assertTrue(project.getWorkersId().contains(this.worker.getId()));

        project = (Project) this.handlersContainer.getCommandHandler(FireWorkerCommandHandler.class).handle(new EngageFireWorkerCommand(
                project.getId(),
                this.worker.getId()
        ));

        assertEquals(0, project.getWorkersId().size());
        assertFalse(project.getWorkersId().contains(this.worker.getId()));
    }

    @Test
    public void engageWorker_workerDoesNotExist()
    {
        // TODO
    }

    @Test
    public void engageWorker_workerAlreadyWorksOnProject()
    {
        // TODO
    }

    @Test
    public void fireWorker_workerDoesNotWorkOnProject()
    {
        // TODO
    }

    @Test
    public void hardDeletingEngagedWorker()
    {
        Project project = (Project) this.handlersContainer.getCommandHandler(CreateProjectCommandHandler.class).handle(new CreateProjectCommand(
                this.contractor.getId(),
                75
        ));

        project = (Project) this.handlersContainer.getCommandHandler(EngageWorkerCommandHandler.class).handle(new EngageFireWorkerCommand(
                project.getId(),
                this.worker.getId()
        ));

        assertEquals(1, project.getWorkersId().size());
        assertTrue(project.getWorkersId().contains(this.worker.getId()));

        assertTrue((boolean) this.handlersContainer.getCommandHandler(DeleteWorkerCommandHandler.class).handle(new DeleteWorkerCommand(
                this.worker.getId()
        )));

        project = (Project) this.handlersContainer.getQueryHandler(ReadProjectQueryHandler.class).handle(new ReadProjectQuery(
                project.getId()
        ));

        assertEquals(0, project.getWorkersId().size());
        assertFalse(project.getWorkersId().contains(this.worker.getId()));
    }
}
