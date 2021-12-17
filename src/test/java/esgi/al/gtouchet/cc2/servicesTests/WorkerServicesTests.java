package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.application.services.ServicesContainer;
import esgi.al.gtouchet.cc2.application.services.worker.CreateWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.dtos.CreateWorkerDto;
import esgi.al.gtouchet.cc2.domain.models.Service;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.MemoryRepositoriesRetainer;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WorkerServicesTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private RepositoriesFactory repositoriesFactory;
    private ServicesContainer servicesContainer;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();
        this.servicesContainer = ServicesContainer.initialize(
                this.repositoriesFactory,
                new PasswordValidator(),
                new PaymentMethodValidatorApi()
        );
    }

    @Test
    public void createWorker()
    {
        long workerRepoSize = this.repositoriesFactory.createWorkerRepository().read().count();

        assertEquals(0, workerRepoSize);

        Worker worker = (Worker) this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        workerRepoSize = this.repositoriesFactory.createWorkerRepository().read().count();

        assertEquals(1, workerRepoSize);
        assertTrue(this.repositoriesFactory.createWorkerRepository().exists(worker.getId()));
    }
/* // TODO
    @Test
    public void deleteWorker()
    {
        Id workerId = this.manager.workerService.create(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        );

        this.manager.workerService.delete(workerId);

        long workerRepoSize = this.manager.workerService.getRepositorySize();

        assertEquals(0, workerRepoSize);
        assertFalse(this.manager.workerService.exists(workerId));
    }

    @Test
    public void createTwoWorkers_sameLogin()
    {
        String login = "GTouchet";
        long workerRepoSize;

        Id workerId1 = this.manager.workerService.create(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        );

        Id workerId2 = this.manager.workerService.create(
                login,
                Password.of("ABcd1234!"),
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
        String login = "GTouchet";
        long workerAndContractorReposSize;

        Id workerId = this.manager.workerService.create(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        );

        Id contractorId = this.manager.contractorService.create(new CreateContractorDto(
                login,
                Password.of("ABcd1234!"),
                "Touchet",
                PaymentMethod.card
        ));

        workerAndContractorReposSize =
                this.manager.workerService.getRepositorySize() +
                this.manager.contractorService.getRepositorySize();

        assertEquals(1, workerAndContractorReposSize);
        assertTrue(this.manager.workerService.exists(workerId));
        assertFalse(this.manager.contractorService.exists(contractorId));
    }

    @Test
    public void updateWorker()
    {
        Id workerId = this.manager.workerService.create(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        );

        Worker originalWorker = this.manager.workerIMR.read(workerId);

        Password newPassword = Password.of("newPass123??");
        String newName = "Robert";
        Service newService = Service.electrician;
        int newDepartment = 75;

        this.manager.workerService.update(workerId,
                newPassword,
                newName,
                newService,
                newDepartment
        );

        Worker updatedWorker = this.manager.workerIMR.read(workerId);

        assertNotSame(originalWorker, updatedWorker);
        Assert.assertEquals(originalWorker.getId(), updatedWorker.getId());
        Assert.assertEquals(updatedWorker.getPassword(), newPassword);
        assertEquals(updatedWorker.getName(), newName);
        assertEquals(updatedWorker.getService(), newService);
        assertEquals(updatedWorker.getDepartment(), newDepartment);
    }

    @Test
    public void unrecognizedService()
    {
        this.exception.expect(IllegalArgumentException.class);

        this.manager.workerService.create(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.valueOf("developer"),
                91
        );
    }

    @Test
    public void departmentNotDigit()
    {
        this.exception.expect(NumberFormatException.class);

        this.manager.workerService.create(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                Integer.parseInt("ninety one")
        );
    }
    */
}
