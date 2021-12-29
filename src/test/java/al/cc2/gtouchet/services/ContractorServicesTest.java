package al.cc2.gtouchet.services;

import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.application.services.dtos.contractor.*;
import al.cc2.gtouchet.application.services.dtos.worker.CreateWorkerCommand;
import al.cc2.gtouchet.application.services.handlers.contractor.*;
import al.cc2.gtouchet.application.services.handlers.worker.CreateWorkerCommandHandler;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.payment.PaymentMethod;
import al.cc2.gtouchet.domain.models.user.WorkerService;
import al.cc2.gtouchet.domain.models.user.Worker;
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

public class ContractorServicesTest
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
    public void createContractor()
    {
        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(0, contractorRepoSize);

        Contractor contractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));

        contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(1, contractorRepoSize);
        assertTrue(this.repositoriesFactory.createContractorRepository().exists(contractor.getId()));
    }

    @Test
    public void deleteContractor()
    {
        Contractor contractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));

        assertTrue((boolean) this.handlersContainer.getCommandHandler(DeleteContractorByIdCommandHandler.class).handle(new DeleteContractorCommand(
                contractor.getId()
        )));

        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(0, contractorRepoSize);
        assertFalse(this.repositoriesFactory.createContractorRepository().exists(contractor.getId()));
    }

    @Test
    public void createTwoContractors_sameLogin()
    {
        final String sameLogin = "GTouchet";

        Contractor contractor1 = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));

        Contractor contractor2 = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));

        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(1, contractorRepoSize);
        assertTrue(this.repositoriesFactory.createContractorRepository().exists(contractor1.getId()));
        assertNull(contractor2);
    }

    @Test
    public void createContractorAndWorker_sameLogin()
    {
        final String sameLogin = "GTouchet";

        Contractor contractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                sameLogin,
                Password.of("ABcd1234!"),
                "Touchet",
                PaymentMethod.CARD
        ));

        Worker worker = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                WorkerService.BUILDER,
                91
        ));

        long contractorAndWorkerReposSize =
                this.repositoriesFactory.createContractorRepository().read().count() +
                        this.repositoriesFactory.createWorkerRepository().read().count();

        assertEquals(1, contractorAndWorkerReposSize);
        assertTrue(this.repositoriesFactory.createContractorRepository().exists(contractor.getId()));
        assertNull(worker);
    }

    @Test
    public void updateContractor()
    {
        Contractor originalContractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));

        Password newPassword = Password.of("newPass123??");
        String newName = "Robert";
        PaymentMethod newPaymentMethod = PaymentMethod.PAYPAL;

        Contractor updatedContractor = (Contractor) this.handlersContainer.getCommandHandler(UpdateContractorByIdCommandHandler.class).handle(new UpdateContractorCommand(
                originalContractor.getId(),
                newPassword,
                newName,
                newPaymentMethod
        ));

        assertNotSame(originalContractor, updatedContractor);
        assertEquals(originalContractor.getId(), updatedContractor.getId());
        assertEquals(updatedContractor.getCredentials().getPassword(), newPassword);
        assertEquals(updatedContractor.getName(), newName);
        assertEquals(updatedContractor.getPaymentMethod(), newPaymentMethod);
    }

    @Test
    public void validatePaymentMethod()
    {
        Contractor contractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));

        assertFalse(contractor.isPaymentValidated());

        assertTrue((boolean) this.handlersContainer.getCommandHandler(ValidatePaymentByIdCommandHandler.class).handle(new ValidatePaymentCommand(
                contractor.getId()
        )));

        contractor = (Contractor) this.handlersContainer.getQueryHandler(ReadContractorByIdQueryHandler.class).handle(new ReadContractorQuery(
                contractor.getId()
        ));
        assertTrue(contractor.isPaymentValidated());
    }

    @Test
    public void unrecognizedPaymentMethod()
    {
        exception.expect(IllegalArgumentException.class);

        this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.valueOf("€4$H")
        ));
    }
}
