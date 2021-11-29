package esgi.al.cc1;

import esgi.al.cc1.domain.dtos.Id;
import esgi.al.cc1.infrastructure.factories.ControllersFactory;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectControllerTests
{
    private final ControllersFactory controllersFactory = new ControllersFactory();

    /**
     * I don't want my "real" data to be polluted by my tests
     * So everytime I run a test, I should call this method to clean my fake data
     * @param id The ID of the element to clean
     */
    private void cleanMyRoomFromFakeProject(Id id)
    {
        this.controllersFactory.createProjectController().remove(id.toString());
    }
    private void cleanMyRoomFromFakeWorker(Id id)
    {
        this.controllersFactory.createWorkerController().remove(id.toString());
    }

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

        this.cleanMyRoomFromFakeProject(newProjectId);
    }

    @Test
    public void createProject_engageWorker_fireWorker()
    {
        Id newProjectId = this.controllersFactory.createProjectController().create(new String[] {"", "75"});
        Id newWorkerId = this.controllersFactory.createWorkerController().create(new String[] {"",
                "123_Touchet_456",
                "__password789__",
                "Guillaume_123",
                "plumber",
                "91"
        });

        // No worker engaged yet
        assertEquals(0, this.controllersFactory.createProjectController().read(newProjectId.toString()).getWorkersId().size());

        // Engaging the new worker
        this.controllersFactory.createProjectController().addWorker(new String[] {"",
                newProjectId.toString(),
                newWorkerId.toString()}
        );
        // Now he's engaged
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

        this.cleanMyRoomFromFakeProject(newProjectId);
        this.cleanMyRoomFromFakeWorker(newWorkerId);
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
                "91"
        });

        this.controllersFactory.createProjectController().addWorker(new String[] {"",
                newProjectId.toString(),
                newWorkerId.toString()}
        );

        // Hard deleting the worker from the worker repo
        this.controllersFactory.createWorkerController().remove(newWorkerId.toString());
        // The worker should have been removed from all his projects automatically
        assertFalse(this.controllersFactory.createProjectController().read(newProjectId.toString()).getWorkersId().stream()
                .anyMatch(id -> id.toString().equals(newWorkerId.toString()))
        );

        this.cleanMyRoomFromFakeProject(newProjectId);
    }

    @Test
    public void createProject_updateProject()
    {
        Id newProjectId = this.controllersFactory.createProjectController().create(new String[] {"", "75"});

        // Updating the project's department
        this.controllersFactory.createProjectController().update(new String[] {"", newProjectId.toString(), "91"});
        // Now it should be in 91
        assertEquals(91, this.controllersFactory.createProjectController().read(newProjectId.toString()).getDepartment());

        this.cleanMyRoomFromFakeProject(newProjectId);
    }
}
