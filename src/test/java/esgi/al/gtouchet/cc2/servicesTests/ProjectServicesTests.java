package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.application.services.ServicesContainer;
import esgi.al.gtouchet.cc2.application.services.contractor.CreateContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.dtos.CreateContractorDto;
import esgi.al.gtouchet.cc2.application.services.project.*;
import esgi.al.gtouchet.cc2.application.services.project.dtos.CreateProjectDto;
import esgi.al.gtouchet.cc2.application.services.project.dtos.EngageFireWorkerDto;
import esgi.al.gtouchet.cc2.application.services.project.dtos.UpdateProjectDto;
import esgi.al.gtouchet.cc2.application.services.worker.CreateWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.DeleteWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.dtos.CreateWorkerDto;
import esgi.al.gtouchet.cc2.domain.models.*;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.MemoryRepositoriesRetainer;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectServicesTests
{
    private RepositoriesFactory repositoriesFactory;
    private ServicesContainer servicesContainer;

    private Contractor contractor;
    private Worker worker;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();
        this.servicesContainer = ServicesContainer.initialize(
                this.repositoriesFactory,
                new PasswordValidator(),
                new PaymentMethodValidatorApi()
        );

        this.contractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet1",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        this.worker = (Worker) this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                "GTouchet2",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));
    }

    @Test
    public void createProject()
    {
        long projectRepoSize = this.repositoriesFactory.createProjectRepository().read().count();

        assertEquals(0, projectRepoSize);

        Project project = (Project) this.servicesContainer.retrieve(CreateProjectServiceHandler.class).handle(new CreateProjectDto(
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
        Project project = (Project) this.servicesContainer.retrieve(CreateProjectServiceHandler.class).handle(new CreateProjectDto(
                this.contractor.getId(),
                75
        ));

        assertTrue((boolean) this.servicesContainer.retrieve(DeleteProjectServiceHandler.class).handle(project.getId()));

        long projectRepoSize = this.repositoriesFactory.createProjectRepository().read().count();

        assertEquals(0, projectRepoSize);
        assertFalse(this.repositoriesFactory.createProjectRepository().exists(project.getId()));
    }

    @Test
    public void updateProject()
    {
        Project originalProject = (Project) this.servicesContainer.retrieve(CreateProjectServiceHandler.class).handle(new CreateProjectDto(
                this.contractor.getId(),
                75
        ));

        Contractor newContractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet3",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));
        int newDepartment = 91;

        Project updatedProject = (Project) this.servicesContainer.retrieve(UpdateProjectServiceHandler.class).handle(new UpdateProjectDto(
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
        Project project = (Project) this.servicesContainer.retrieve(CreateProjectServiceHandler.class).handle(new CreateProjectDto(
                this.contractor.getId(),
                75
        ));

        project = (Project) this.servicesContainer.retrieve(EngageWorkerServiceHandler.class).handle(new EngageFireWorkerDto(
                project.getId(),
                this.worker.getId()
        ));

        assertEquals(1, project.getWorkersId().size());
        assertTrue(project.getWorkersId().contains(this.worker.getId()));
    }

    @Test
    public void fireWorker()
    {
        Project project = (Project) this.servicesContainer.retrieve(CreateProjectServiceHandler.class).handle(new CreateProjectDto(
                this.contractor.getId(),
                75
        ));

        project = (Project) this.servicesContainer.retrieve(EngageWorkerServiceHandler.class).handle(new EngageFireWorkerDto(
                project.getId(),
                this.worker.getId()
        ));

        assertEquals(1, project.getWorkersId().size());
        assertTrue(project.getWorkersId().contains(this.worker.getId()));

        project = (Project) this.servicesContainer.retrieve(FireWorkerServiceHandler.class).handle(new EngageFireWorkerDto(
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
        Project project = (Project) this.servicesContainer.retrieve(CreateProjectServiceHandler.class).handle(new CreateProjectDto(
                this.contractor.getId(),
                75
        ));

        project = (Project) this.servicesContainer.retrieve(EngageWorkerServiceHandler.class).handle(new EngageFireWorkerDto(
                project.getId(),
                this.worker.getId()
        ));

        assertEquals(1, project.getWorkersId().size());
        assertTrue(project.getWorkersId().contains(this.worker.getId()));

        assertTrue((boolean) this.servicesContainer.retrieve(DeleteWorkerServiceHandler.class).handle(this.worker.getId()));

        project = (Project) this.servicesContainer.retrieve(ReadIdProjectServiceHandler.class).handle(project.getId());

        assertEquals(0, project.getWorkersId().size());
        assertFalse(project.getWorkersId().contains(this.worker.getId()));
    }
}
