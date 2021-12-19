package al.cc2.gtouchet.services;

import al.cc2.gtouchet.application.services.ServicesContainer;
import al.cc2.gtouchet.application.services.contractor.CreateContractorServiceHandler;
import al.cc2.gtouchet.application.services.contractor.dtos.CreateContractorDto;
import al.cc2.gtouchet.application.services.worker.CreateWorkerServiceHandler;
import al.cc2.gtouchet.application.services.worker.DeleteWorkerServiceHandler;
import al.cc2.gtouchet.application.services.worker.UpdateWorkerServiceHandler;
import al.cc2.gtouchet.application.services.worker.dtos.CreateWorkerDto;
import al.cc2.gtouchet.application.services.worker.dtos.UpdateWorkerDto;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.models.Service;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.domain.valueObjects.Password;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.factories.MemoryRepositoriesRetainer;
import al.cc2.gtouchet.infrastructure.repositories.factories.RepositoriesFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class WorkerServicesTest
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

    @Test
    public void deleteWorker()
    {
        Worker worker = (Worker) this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        assertTrue((boolean) this.servicesContainer.retrieve(DeleteWorkerServiceHandler.class).handle(worker.getId()));

        long workerRepoSize = this.repositoriesFactory.createWorkerRepository().read().count();

        assertEquals(0, workerRepoSize);
        assertFalse(this.repositoriesFactory.createWorkerRepository().exists(worker.getId()));
    }

    @Test
    public void createTwoWorkers_sameLogin()
    {
        String login = "GTouchet";

        Worker worker1 = (Worker) this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        Worker worker2 = (Worker) this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        long workerRepoSize = this.repositoriesFactory.createWorkerRepository().read().count();

        assertEquals(1, workerRepoSize);
        assertTrue(this.repositoriesFactory.createWorkerRepository().exists(worker1.getId()));
        assertNull(worker2);
    }

    @Test
    public void createWorkerAndContractor_sameLogin()
    {
        String login = "GTouchet";
        long workerAndContractorReposSize;

        Worker worker = (Worker) this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        Contractor contractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                login,
                Password.of("ABcd1234!"),
                "Touchet",
                PaymentMethod.card
        ));

        workerAndContractorReposSize =
                this.repositoriesFactory.createWorkerRepository().read().count() +
                        this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(1, workerAndContractorReposSize);
        assertTrue(this.repositoriesFactory.createWorkerRepository().exists(worker.getId()));
        assertNull(contractor);
    }

    @Test
    public void updateWorker()
    {
        Worker originalWorker = (Worker) this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        Password newPassword = Password.of("newPass123??");
        String newName = "Robert";
        Service newService = Service.electrician;
        int newDepartment = 75;

        Worker updatedWorker = (Worker) this.servicesContainer.retrieve(UpdateWorkerServiceHandler.class).handle(new UpdateWorkerDto(
                originalWorker.getId(),
                newPassword,
                newName,
                newService,
                newDepartment
        ));

        assertNotSame(originalWorker, updatedWorker);
        assertEquals(originalWorker.getId(), updatedWorker.getId());
        assertEquals(updatedWorker.getPassword(), newPassword);
        assertEquals(updatedWorker.getName(), newName);
        assertEquals(updatedWorker.getService(), newService);
        assertEquals(updatedWorker.getDepartment(), newDepartment);
    }

    @Test
    public void unrecognizedService()
    {
        this.exception.expect(IllegalArgumentException.class);

        this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.valueOf("developer"),
                91
        ));
    }

    @Test
    public void departmentNotDigit()
    {
        this.exception.expect(NumberFormatException.class);

        this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                Integer.parseInt("ninety one")
        ));
    }
}
