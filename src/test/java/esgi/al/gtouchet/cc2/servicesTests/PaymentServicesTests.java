package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.application.services.ServicesContainer;
import esgi.al.gtouchet.cc2.application.services.contractor.CreateContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.ValidatePaymentServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.dtos.CreateContractorDto;
import esgi.al.gtouchet.cc2.application.services.payment.CreatePaymentServiceHandler;
import esgi.al.gtouchet.cc2.application.services.payment.DeletePaymentServiceHandler;
import esgi.al.gtouchet.cc2.application.services.payment.dtos.CreatePaymentDto;
import esgi.al.gtouchet.cc2.application.services.worker.CreateWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.dtos.CreateWorkerDto;
import esgi.al.gtouchet.cc2.domain.models.*;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.MemoryRepositoriesRetainer;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class PaymentServicesTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private RepositoriesFactory repositoriesFactory;
    private ServicesContainer servicesContainer;

    private Contractor contractor;
    private Worker worker;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();
        this.servicesContainer = ServicesContainer.initialize(
                this.repositoriesFactory,
                new PasswordValidator(),
                new PaymentMethodValidatorApi()
        );

        this.contractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet1",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));
        this.servicesContainer.retrieve(ValidatePaymentServiceHandler.class).handle(this.contractor.getId());

        this.worker = (Worker) this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                "GTouchet2",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));
    }

    @Test
    public void createPayment()
    {
        long paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);

        Payment payment = (Payment) this.servicesContainer.retrieve(CreatePaymentServiceHandler.class).handle(new CreatePaymentDto(
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
        Payment payment = (Payment) this.servicesContainer.retrieve(CreatePaymentServiceHandler.class).handle(new CreatePaymentDto(
                this.contractor.getId(),
                this.worker.getId(),
                2,
                "Croissant"
        ));

        assertTrue((boolean) this.servicesContainer.retrieve(DeletePaymentServiceHandler.class).handle(payment.getId()));

        long paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);
        assertFalse(this.repositoriesFactory.createPaymentRepository().exists(payment.getId()));
    }

    @Test
    public void createPayment_unvalidatedPaymentMethod()
    {
        long paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);

        Contractor unvalidatedContractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet3",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));
        assertFalse(unvalidatedContractor.isPaymentValidated());

        Payment payment = (Payment) this.servicesContainer.retrieve(CreatePaymentServiceHandler.class).handle(new CreatePaymentDto(
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
