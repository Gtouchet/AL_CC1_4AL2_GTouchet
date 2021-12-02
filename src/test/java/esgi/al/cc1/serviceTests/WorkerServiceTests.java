package esgi.al.cc1.serviceTests;

import esgi.al.cc1.ServicesAndRepositoriesManager;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import esgi.al.cc1.infrastructure.repositories.EntityNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerServiceTests
{
    private ServicesAndRepositoriesManager manager;

    @Test
    public void createWorker()
    {
        this.manager = new ServicesAndRepositoriesManager();
        long workerRepoSize;

        // No worker registered yet
        workerRepoSize = this.manager.workerService.getRepositorySize();
        assertEquals(0, workerRepoSize);

        // Create a worker
        Id workerId = this.manager.workerService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        // Now there is one worker registered
        workerRepoSize = this.manager.workerService.getRepositorySize();
        assertEquals(1, workerRepoSize);
        assertTrue(this.manager.workerService.exists(workerId));
    }

    @Test
    public void createWorker_deleteWorker()
    {
        this.manager = new ServicesAndRepositoriesManager();
        long workerRepoSize;

        Id workerId = this.manager.workerService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        // Deleting the worker
        this.manager.workerService.delete(workerId);

        // No more worker registered
        workerRepoSize = this.manager.workerService.getRepositorySize();
        assertEquals(0, workerRepoSize);
        assertFalse(this.manager.workerService.exists(workerId));
    }

    @Test
    public void createTwoWorkers_sameLogin()
    {
        this.manager = new ServicesAndRepositoriesManager();
        final String login = "GTouchet";
        long workerRepoSize;

        // Creating a first worker with login
        Id workerId1 = this.manager.workerService.create(
                login,
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        // Creating a second worker with same login
        Id workerId2 = this.manager.workerService.create(
                login,
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        // Only one worker got created
        workerRepoSize = this.manager.workerService.getRepositorySize();
        assertEquals(1, workerRepoSize);

        // The first one got created
        assertTrue(this.manager.workerService.exists(workerId1));
        // But not the second one
        assertFalse(this.manager.workerService.exists(workerId2));
    }

    @Test
    public void createWorker_createContractor_sameLogin()
    {
        this.manager = new ServicesAndRepositoriesManager();
        final String login = "GTouchet";
        long workerAndContractorReposSize;

        // Creating worker with login
        Id workerId = this.manager.workerService.create(
                login,
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        // Creating contractor with same login
        Id contractorId = this.manager.contractorService.create(
                login,
                Password.of("123pass"),
                "Touchet",
                PaymentMethod.card
        );

        // Only one of them got created
        workerAndContractorReposSize =
                this.manager.workerService.getRepositorySize() +
                this.manager.contractorService.getRepositorySize();
        assertEquals(1, workerAndContractorReposSize);

        // The worker got created
        assertTrue(this.manager.workerService.exists(workerId));
        // But not the contractor
        assertFalse(this.manager.contractorService.exists(contractorId));
    }

    @Test
    public void createWorker_updateWorker() throws EntityNotFoundException
    {
        this.manager = new ServicesAndRepositoriesManager();

        Id workerId = this.manager.workerService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        final Password newPassword = Password.of("456pass");
        final String newName = "Robert";
        final Service newService = Service.electrician;
        final int newDepartment = 75;

        // Updating the worker
        this.manager.workerService.update(workerId,
                newPassword,
                newName,
                newService,
                newDepartment
        );

        // The worker's properties got updated
        Worker updatedWorker = this.manager.workerIMR.read(workerId);
        assertEquals(newPassword, updatedWorker.getPassword());
        assertEquals(newName, updatedWorker.getName());
        assertEquals(newService, updatedWorker.getService());
        assertEquals(newDepartment, updatedWorker.getDepartment());

        // But not his ID
        assertEquals(workerId, updatedWorker.getId());
    }
}
