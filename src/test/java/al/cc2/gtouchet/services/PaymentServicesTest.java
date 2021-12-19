package al.cc2.gtouchet.services;

import al.cc2.gtouchet.application.services.ServicesContainer;
import al.cc2.gtouchet.application.services.contractor.CreateContractorServiceHandler;
import al.cc2.gtouchet.application.services.contractor.ValidatePaymentServiceHandler;
import al.cc2.gtouchet.application.services.contractor.dtos.CreateContractorDto;
import al.cc2.gtouchet.application.services.payment.CreatePaymentServiceHandler;
import al.cc2.gtouchet.application.services.payment.DeletePaymentServiceHandler;
import al.cc2.gtouchet.application.services.payment.dtos.CreatePaymentDto;
import al.cc2.gtouchet.application.services.worker.CreateWorkerServiceHandler;
import al.cc2.gtouchet.application.services.worker.dtos.CreateWorkerDto;
import al.cc2.gtouchet.domain.models.*;
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
