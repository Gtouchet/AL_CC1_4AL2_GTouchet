package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.application.services.contractor.ContractorServicesFactory;
import esgi.al.gtouchet.cc2.application.services.contractor.create.CreateContractorDto;
import esgi.al.gtouchet.cc2.application.services.factories.MemoryServicesFactory;
import esgi.al.gtouchet.cc2.application.services.factories.ServicesFactory;
import esgi.al.gtouchet.cc2.application.services.payment.PaymentServicesFactory;
import esgi.al.gtouchet.cc2.application.services.payment.create.CreatePaymentDto;
import esgi.al.gtouchet.cc2.application.services.worker.WorkerServicesFactory;
import esgi.al.gtouchet.cc2.application.services.worker.create.CreateWorkerDto;
import esgi.al.gtouchet.cc2.domain.models.*;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
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

    private ContractorServicesFactory contractorServicesFactory;
    private PaymentServicesFactory paymentServicesFactory;

    private Contractor contractor;
    private Worker worker;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();

        ServicesFactory servicesFactory = new MemoryServicesFactory(this.repositoriesFactory);
        this.contractorServicesFactory = servicesFactory.createContractorServicesFactory();
        this.paymentServicesFactory = servicesFactory.createPaymentServicesFactory();
        WorkerServicesFactory workerServicesFactory = servicesFactory.createWorkerServicesFactory();

        this.contractor = this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                "GTouchet1",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));
        this.contractorServicesFactory.getValidatePaymentService().handle(this.contractor.getId());

        this.worker = workerServicesFactory.getCreateWorkerHandler().handle(new CreateWorkerDto(
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

        Payment payment = this.paymentServicesFactory.getCreatePaymentHandler().handle(new CreatePaymentDto(
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
        Payment payment = this.paymentServicesFactory.getCreatePaymentHandler().handle(new CreatePaymentDto(
                this.contractor.getId(),
                this.worker.getId(),
                2,
                "Croissant"
        ));

        this.paymentServicesFactory.getDeletePaymentHandler().handle(payment.getId());

        long paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);
        assertFalse(this.repositoriesFactory.createPaymentRepository().exists(payment.getId()));
    }

    @Test
    public void createPayment_unvalidatedPaymentMethod()
    {
        long paymentRepoSize = this.repositoriesFactory.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);

        Contractor unvalidatedContractor = this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                "GTouchet3",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));
        assertFalse(unvalidatedContractor.isPaymentValidated());

        Payment payment = this.paymentServicesFactory.getCreatePaymentHandler().handle(new CreatePaymentDto(
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
