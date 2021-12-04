package esgi.al.cc1.serviceTests;

import esgi.al.cc1.ServicesAndRepositoriesManager;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import esgi.al.cc1.infrastructure.repositories.EntityNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class WorkerServiceTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private ServicesAndRepositoriesManager manager;

    @Test
    public void createWorker()
    {
        this.manager = new ServicesAndRepositoriesManager();
        long workerRepoSize;

        workerRepoSize = this.manager.workerService.getRepositorySize();
        assertEquals(0, workerRepoSize);

        Id workerId = this.manager.workerService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        workerRepoSize = this.manager.workerService.getRepositorySize();
        assertEquals(1, workerRepoSize);
        assertTrue(this.manager.workerService.exists(workerId));
    }

    @Test
    public void deleteWorker()
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

        this.manager.workerService.delete(workerId);

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

        Id workerId1 = this.manager.workerService.create(
                login,
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        Id workerId2 = this.manager.workerService.create(
                login,
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        workerRepoSize = this.manager.workerService.getRepositorySize();
        assertEquals(1, workerRepoSize);

        assertTrue(this.manager.workerService.exists(workerId1));
        assertFalse(this.manager.workerService.exists(workerId2));
    }

    @Test
    public void createWorkerAndContractor_sameLogin()
    {
        this.manager = new ServicesAndRepositoriesManager();
        final String login = "GTouchet";
        long workerAndContractorReposSize;

        Id workerId = this.manager.workerService.create(
                login,
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                91
        );

        Id contractorId = this.manager.contractorService.create(
                login,
                Password.of("123pass"),
                "Touchet",
                PaymentMethod.card
        );

        workerAndContractorReposSize =
                this.manager.workerService.getRepositorySize() +
                this.manager.contractorService.getRepositorySize();
        assertEquals(1, workerAndContractorReposSize);

        assertTrue(this.manager.workerService.exists(workerId));
        assertFalse(this.manager.contractorService.exists(contractorId));
    }

    @Test
    public void updateWorker() throws EntityNotFoundException
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

        this.manager.workerService.update(workerId,
                newPassword,
                newName,
                newService,
                newDepartment
        );

        Worker updatedWorker = this.manager.workerIMR.read(workerId);
        assertEquals(newPassword, updatedWorker.getPassword());
        assertEquals(newName, updatedWorker.getName());
        assertEquals(newService, updatedWorker.getService());
        assertEquals(newDepartment, updatedWorker.getDepartment());

        assertEquals(workerId, updatedWorker.getId());
    }

    @Test
    public void unrecognizedService()
    {
        this.manager = new ServicesAndRepositoriesManager();
        this.exception.expect(IllegalArgumentException.class);

        this.manager.workerService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                Service.valueOf("developer"),
                91
        );
    }

    @Test
    public void departmentNotDigit()
    {
        this.manager = new ServicesAndRepositoriesManager();
        this.exception.expect(NumberFormatException.class);

        this.manager.workerService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                Service.builder,
                Integer.parseInt("ninety one")
        );
    }

    @Test
    public void invalidPassword()
    {
        // Todo, not implemented yet :s
    }
}
