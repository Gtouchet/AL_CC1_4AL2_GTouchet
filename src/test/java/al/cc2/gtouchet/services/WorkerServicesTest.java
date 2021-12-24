package al.cc2.gtouchet.services;

import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.application.services.dtos.contractor.CreateContractorCommand;
import al.cc2.gtouchet.application.services.dtos.worker.CreateWorkerCommand;
import al.cc2.gtouchet.application.services.dtos.worker.DeleteWorkerCommand;
import al.cc2.gtouchet.application.services.dtos.worker.UpdateWorkerCommand;
import al.cc2.gtouchet.application.services.handlers.contractor.CreateContractorCommandHandler;
import al.cc2.gtouchet.application.services.handlers.worker.CreateWorkerCommandHandler;
import al.cc2.gtouchet.application.services.handlers.worker.DeleteWorkerCommandHandler;
import al.cc2.gtouchet.application.services.handlers.worker.UpdateWorkerCommandHandler;
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
    private HandlersContainer handlersContainer;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();
        this.handlersContainer = HandlersContainer.initialize(
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

        Worker worker = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
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
        Worker worker = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        assertTrue((boolean) this.handlersContainer.getCommandHandler(DeleteWorkerCommandHandler.class).handle(new DeleteWorkerCommand(
                worker.getId()
        )));

        long workerRepoSize = this.repositoriesFactory.createWorkerRepository().read().count();

        assertEquals(0, workerRepoSize);
        assertFalse(this.repositoriesFactory.createWorkerRepository().exists(worker.getId()));
    }

    @Test
    public void createTwoWorkers_sameLogin()
    {
        String login = "GTouchet";

        Worker worker1 = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        Worker worker2 = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
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

        Worker worker = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        Contractor contractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
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
        Worker originalWorker = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
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

        Worker updatedWorker = (Worker) this.handlersContainer.getCommandHandler(UpdateWorkerCommandHandler.class).handle(new UpdateWorkerCommand(
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

        this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
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

        this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                Integer.parseInt("ninety one")
        ));
    }
}
