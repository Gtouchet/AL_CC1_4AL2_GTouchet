package al.cc2.gtouchet.services;

import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.application.services.dtos.contractor.CreateContractorCommand;
import al.cc2.gtouchet.application.services.dtos.contractor.ValidatePaymentCommand;
import al.cc2.gtouchet.application.services.dtos.payment.CreatePaymentCommand;
import al.cc2.gtouchet.application.services.dtos.payment.DeletePaymentCommand;
import al.cc2.gtouchet.application.services.dtos.worker.CreateWorkerCommand;
import al.cc2.gtouchet.application.services.handlers.contractor.CreateContractorCommandHandler;
import al.cc2.gtouchet.application.services.handlers.contractor.ValidatePaymentCommandHandler;
import al.cc2.gtouchet.application.services.handlers.payment.CreatePaymentCommandHandler;
import al.cc2.gtouchet.application.services.handlers.payment.DeletePaymentCommandHandler;
import al.cc2.gtouchet.application.services.handlers.worker.CreateWorkerCommandHandler;
import al.cc2.gtouchet.domain.models.payment.Payment;
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

public class PaymentServicesTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private RepositoriesFactory repositoriesFactory;
    private HandlersContainer handlersContainer;

    private Contractor contractor;
    private Worker worker;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();
        this.handlersContainer = HandlersContainer.initialize(
                this.repositoriesFactory,
                new PasswordValidator(),
                new PaymentMethodValidatorApi()
        );

        this.contractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                "GTouchet1",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));
        this.handlersContainer.getCommandHandler(ValidatePaymentCommandHandler.class).handle(new ValidatePaymentCommand(
                this.contractor.getId()
        ));

        this.worker = (Worker) this.handlersContainer.getCommandHandler(CreateWorkerCommandHandler.class).handle(new CreateWorkerCommand(
                "GTouchet2",
                Password.of("ABcd1234!"),
                "Guillaume",
                WorkerService.BUILDER,
                91
        ));
    }

    @Test
    public void createPayment()
    {
        long paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);

        Payment payment = (Payment) this.handlersContainer.getCommandHandler(CreatePaymentCommandHandler.class).handle(new CreatePaymentCommand(
                this.contractor.getId(),
                this.worker.getId(),
                1.25,
                "Coffee"
        ));

        paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(1, paymentRepoSize);
        assertTrue(this.repositoriesFactory.createPaymentRepository().exists(payment.getId()));
    }

    @Test
    public void deletePayment()
    {
        Payment payment = (Payment) this.handlersContainer.getCommandHandler(CreatePaymentCommandHandler.class).handle(new CreatePaymentCommand(
                this.contractor.getId(),
                this.worker.getId(),
                2,
                "Croissant"
        ));

        assertTrue((boolean) this.handlersContainer.getCommandHandler(DeletePaymentCommandHandler.class).handle(new DeletePaymentCommand(
                payment.getId()
        )));

        long paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);
        assertFalse(this.repositoriesFactory.createPaymentRepository().exists(payment.getId()));
    }

    @Test
    public void createPayment_unvalidatedPaymentMethod()
    {
        long paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);

        Contractor unvalidatedContractor = (Contractor) this.handlersContainer.getCommandHandler(CreateContractorCommandHandler.class).handle(new CreateContractorCommand(
                "GTouchet3",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.CARD
        ));
        assertFalse(unvalidatedContractor.isPaymentValidated());

        Payment payment = (Payment) this.handlersContainer.getCommandHandler(CreatePaymentCommandHandler.class).handle(new CreatePaymentCommand(
                unvalidatedContractor.getId(),
                this.worker.getId(),
                9.90,
                "Lunch"
        ));

        paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);
        assertNull(payment);
    }
}
