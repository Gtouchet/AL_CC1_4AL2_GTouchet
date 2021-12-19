package al.cc2.gtouchet.services;

import al.cc2.gtouchet.application.services.ServicesContainer;
import al.cc2.gtouchet.application.services.contractor.CreateContractorServiceHandler;
import al.cc2.gtouchet.application.services.contractor.dtos.CreateContractorDto;
import al.cc2.gtouchet.application.services.project.*;
import al.cc2.gtouchet.application.services.project.dtos.CreateProjectDto;
import al.cc2.gtouchet.application.services.project.dtos.EngageFireWorkerDto;
import al.cc2.gtouchet.application.services.project.dtos.UpdateProjectDto;
import al.cc2.gtouchet.application.services.worker.CreateWorkerServiceHandler;
import al.cc2.gtouchet.application.services.worker.DeleteWorkerServiceHandler;
import al.cc2.gtouchet.application.services.worker.dtos.CreateWorkerDto;
import al.cc2.gtouchet.domain.models.*;
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
