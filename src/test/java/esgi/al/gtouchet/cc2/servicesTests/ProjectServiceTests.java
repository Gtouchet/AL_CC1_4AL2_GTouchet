package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.application.contractorServices.create.CreateContractorDto;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.models.Service;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ProjectServiceTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();
/* // TODO
    private ServicesAndRepositoriesManager manager;
    private Id contractorId;
    private Id workerId;

    @Before
    public void setup()
    {
        this.manager = new ServicesAndRepositoriesManager();

        this.contractorId = this.manager.contractorService.create(new CreateContractorDto(
                "GTouchet1",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        this.workerId = this.manager.workerService.create(
                "GTouchet2",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        );
    }

    @Test
    public void createProject()
    {
        long projectRepoSize = this.manager.projectService.getRepositorySize();

        assertEquals(0, projectRepoSize);

        Id projectId = this.manager.projectService.create(
                this.contractorId,
                75
        );

        projectRepoSize = this.manager.projectService.getRepositorySize();
        assertEquals(1, projectRepoSize);
        assertTrue(this.manager.projectService.exists(projectId));
    }

    @Test
    public void deleteProject()
    {
        Id projectId = this.manager.projectService.create(
                this.contractorId,
                75
        );

        this.manager.projectService.delete(projectId);

        long projectRepoSize = this.manager.projectService.getRepositorySize();

        assertEquals(0, projectRepoSize);
        assertFalse(this.manager.projectService.exists(projectId));
    }

    @Test
    public void updateProject() throws EntityNotFoundException
    {
        Id projectId = this.manager.projectService.create(
                this.contractorId,
                75
        );

        Project originalProject = this.manager.projectIMR.read(projectId);

        Id newContractorId = this.manager.contractorService.create(new CreateContractorDto(
                "GTouchetContractor",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));
        int newDepartment = 91;

        this.manager.projectService.update(projectId,
                newContractorId,
                newDepartment
        );

        Project updatedProject = this.manager.projectIMR.read(projectId);

        assertNotSame(originalProject, updatedProject);
        assertEquals(originalProject.getId(), updatedProject.getId());
        assertEquals(updatedProject.getContractorId(), newContractorId);
        assertEquals(updatedProject.getDepartment(), newDepartment);
    }

    @Test
    public void engageWorker() throws EntityNotFoundException
    {
        Id projectId = this.manager.projectService.create(
                this.contractorId,
                75
        );

        this.manager.projectService.engageWorker(projectId, this.workerId);

        Project project = this.manager.projectIMR.read(projectId);

        assertEquals(1, project.getWorkersId().size());
        assertTrue(project.getWorkersId().contains(this.workerId));
    }

    @Test
    public void fireWorker() throws EntityNotFoundException
    {
        Id projectId = this.manager.projectService.create(
                this.contractorId,
                75
        );

        this.manager.projectService.engageWorker(projectId, this.workerId);

        Project project = this.manager.projectIMR.read(projectId);

        assertEquals(1, project.getWorkersId().size());
        assertTrue(project.getWorkersId().contains(this.workerId));

        this.manager.projectService.fireWorker(projectId, this.workerId);

        project = this.manager.projectIMR.read(projectId);

        assertEquals(0, project.getWorkersId().size());
        assertFalse(project.getWorkersId().contains(this.workerId));
    }

    @Test
    public void hardDeletingEngagedWorker() throws EntityNotFoundException
    {
        Id projectId = this.manager.projectService.create(
                this.contractorId,
                75
        );

        this.manager.projectService.engageWorker(projectId, this.workerId);

        Project project = this.manager.projectIMR.read(projectId);

        assertEquals(1, project.getWorkersId().size());
        assertTrue(project.getWorkersId().contains(this.workerId));

        this.manager.workerService.delete(workerId);

        project = this.manager.projectIMR.read(projectId);

        assertEquals(0, project.getWorkersId().size());
        assertFalse(project.getWorkersId().contains(this.workerId));
    }*/
}
