package esgi.al.cc1.serviceTests;

import esgi.al.cc1.ServicesAndRepositoriesManager;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import esgi.al.cc1.infrastructure.repositories.EntityNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ProjectServiceTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private ServicesAndRepositoriesManager manager;

    @Before
    public void setup()
    {
        this.manager = new ServicesAndRepositoriesManager();
    }

    @Test
    public void createProject()
    {

    }

    @Test
    public void updateProject() throws EntityNotFoundException
    {
        Id originalContractorId = this.manager.contractorService.create(
                "GTouchet1",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );

        Id projectId = this.manager.projectService.create(
                originalContractorId,
                75
        );

        Project originalProject = this.manager.projectIMR.read(projectId);

        Id newContractorId = this.manager.contractorService.create(
                "GTouchet2",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );
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
}
