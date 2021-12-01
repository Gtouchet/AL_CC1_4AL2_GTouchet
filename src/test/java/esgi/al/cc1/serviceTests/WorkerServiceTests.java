package esgi.al.cc1.serviceTests;

import esgi.al.cc1.ServicesAndRepositoriesManager;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerServiceTests extends ServicesAndRepositoriesManager
{
    @Test
    public void createWorker()
    {
        this.resetServiceAndRepositories();

        // No worker registered yet
        long repositorySize = this.workerIMR.read().count();
        assertEquals(0, repositorySize);

        // Creating a worker
        this.workerService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        // Now there is a worker registered
        repositorySize = this.workerIMR.read().count();
        assertEquals(1, repositorySize);
    }

    @Test
    public void createWorker_deleteWorker()
    {
        this.resetServiceAndRepositories();

        Id workerId = this.workerService.create(
                "GTouchet",
                Password.of("123pass456"),
                "Guillaume",
                Service.builder,
                91
        );

        // There is a worker registered
        long repositorySize = this.workerIMR.read().count();
        assertEquals(1, repositorySize);
        assertTrue(this.workerIMR.exists(workerId));

        // Deleting the worker
        this.workerService.delete(workerId);

        // No more worker registered
        repositorySize = this.workerIMR.read().count();
        assertEquals(0, repositorySize);
        assertFalse(this.workerIMR.exists(workerId));
    }

    @Test
    public void createTwoWorkers_sameLogin()
    {
        this.resetServiceAndRepositories();

        final String login = "GTouchet";

        // Creating the first worker with login
        Id workerId1 = this.workerService.create(
                login,
                Password.of("123pass456"),
                "Guillaume",
                Service.builder,
                91
        );

        // Trying to create a second worker with same login
        Id workerId2 = this.workerService.create(
                login,
                Password.of("bonjour789"),
                "Touchet",
                Service.electrician,
                75
        );

        // The first worker was created
        assertTrue(this.workerIMR.exists(workerId1));
        // But not the second one
        assertFalse(this.workerIMR.exists(workerId2));
    }

    @Test
    public void createWorker_createContractor_sameLogin()
    {
        this.resetServiceAndRepositories();

        final String login = "GTouchet";

        // Creating the first worker with login
        Id workerId = this.workerService.create(
                login,
                Password.of("123pass456"),
                "Guillaume",
                Service.builder,
                91
        );

        // Trying to create a contractor with same login
        Id contractorId = this.contractorService.create(
                login,
                Password.of("bonjour789"),
                "Touchet",
                PaymentMethod.card
        );

        // The worker was created
        assertTrue(this.workerIMR.exists(workerId));
        // But not the contractor
        assertFalse(this.contractorIMR.exists(contractorId));
    }

    @Test
    public void createWorker_updateWorker()
    {
        this.resetServiceAndRepositories();

        Id workerId = this.workerService.create(
                "GTouchet",
                Password.of("123pass456"),
                "Guillaume",
                Service.builder,
                91
        );

        // Updating worker's password, name, service and department
        final Password newPassword = Password.of("?1234abcd!");
        final String newName = "Robert";
        final Service newService = Service.electrician;
        final int newDepartment = 75;

        this.workerService.update(workerId,
                newPassword,
                newName,
                newService,
                newDepartment
        );

        // Worker has been updated
        Worker updatedWorker = this.workerIMR.read(workerId);
        assertEquals(newPassword, updatedWorker.getPassword());
        assertEquals(newName, updatedWorker.getName());
        assertEquals(newService, updatedWorker.getService());
        assertEquals(newDepartment, updatedWorker.getDepartment());
        // But not his ID
        assertEquals(workerId, updatedWorker.getId());
    }
}
