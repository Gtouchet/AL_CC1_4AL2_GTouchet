package esgi.al.cc1;

import esgi.al.cc1.domain.dtos.Id;
import esgi.al.cc1.infrastructure.factories.ControllersFactory;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectControllerTests extends RoomCleaner
{
    private final ControllersFactory controllersFactory = new ControllersFactory();

    @Test
    public void createProject()
    {
        // Get the repo's actual size
        long initialRepoSize = this.controllersFactory.createProjectController().read().count();
        // Add a new project in the repo
        Id newProjectId = this.controllersFactory.createProjectController().create(new String[] {"", "75"});
        // Get the repo's new size
        long newRepoSize = this.controllersFactory.createProjectController().read().count();

        // Check the difference between old and new sizes
        assertEquals(1, newRepoSize - initialRepoSize);

        this.cleanFakeProject(newProjectId);
    }

    @Test
    public void createProject_updateProject()
    {
        Id newProjectId = this.controllersFactory.createProjectController().create(new String[] {"", "75"});

        // Project's department is 75
        assertEquals(75, this.controllersFactory.createProjectController().read(newProjectId.toString()).getDepartment());
        // Updating the project's department
        this.controllersFactory.createProjectController().update(new String[] {"", newProjectId.toString(), "91"});
        // Now it should be 91
        assertEquals(91, this.controllersFactory.createProjectController().read(newProjectId.toString()).getDepartment());

        this.cleanFakeProject(newProjectId);
    }

    @Test
    public void createProject_engageWorker_fireWorker()
    {
        Id newProjectId = this.controllersFactory.createProjectController().create(new String[] {"", "75"});
        Id newWorkerId = this.controllersFactory.createWorkerController().create(new String[] {"",
                "123_Touchet_456",
                "__password789__",
                "Guillaume",
                "plumber",
                "91",
        });

        // No worker engaged yet
        assertEquals(0, this.controllersFactory.createProjectController().read(newProjectId.toString()).getWorkersId().size());

        // Engaging the new worker
        this.controllersFactory.createProjectController().addWorker(new String[] {"",
                newProjectId.toString(),
                newWorkerId.toString()}
        );
        // Now he's working on the project
        assertTrue(this.controllersFactory.createProjectController().read(newProjectId.toString()).getWorkersId().stream()
                .anyMatch(id -> id.toString().equals(newWorkerId.toString()))
        );

        // Firing the new worker
        this.controllersFactory.createProjectController().removeWorker(new String[] {"",
                newProjectId.toString(),
                newWorkerId.toString()}
        );
        // The worker no longer works on the project
        assertFalse(this.controllersFactory.createProjectController().read(newProjectId.toString()).getWorkersId().stream()
                .anyMatch(id -> id.toString().equals(newWorkerId.toString()))
        );
        assertEquals(0, this.controllersFactory.createProjectController().read(newProjectId.toString()).getWorkersId().size());

        this.cleanFakeProject(newProjectId);
        this.cleanFakeWorker(newWorkerId);
    }

    @Test
    public void createProject_engageWorker_deleteWorker()
    {
        Id newProjectId = this.controllersFactory.createProjectController().create(new String[] {"", "75"});
        Id newWorkerId = this.controllersFactory.createWorkerController().create(new String[] {"",
                "123_Touchet_456",
                "__password789__",
                "Guillaume_123",
                "plumber",
                "91",
        });

        this.controllersFactory.createProjectController().addWorker(new String[] {"",
                newProjectId.toString(),
                newWorkerId.toString()}
        );

        // Hard deleting the worker from the worker repo
        this.controllersFactory.createWorkerController().remove(newWorkerId.toString());
        // The worker has been removed from all his projects automatically
        assertFalse(this.controllersFactory.createProjectController().read(newProjectId.toString()).getWorkersId().stream()
                .anyMatch(id -> id.toString().equals(newWorkerId.toString()))
        );

        this.cleanFakeWorker(newProjectId);
    }
}
