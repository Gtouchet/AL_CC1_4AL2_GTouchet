package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class PaymentServicesTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private RepositoriesFactory repositoriesRetainer;
/*
    private ContractorServicesFactory contractorServicesFactory;
    private PaymentServicesFactory paymentServicesFactory;

    private Contractor contractor;
    private Worker worker;

    @Before
    public void setup()
    {
        this.repositoriesRetainer = new MemoryRepositoriesRetainer();

        ServicesFactory servicesFactory = new ServicesFactory(this.repositoriesRetainer);
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
        long paymentRepoSize = this.repositoriesRetainer.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);

        Payment payment = this.paymentServicesFactory.getCreatePaymentHandler().handle(new CreatePaymentDto(
                this.contractor.getId(),
                this.worker.getId(),
                1.25,
                "Coffee"
        ));

        paymentRepoSize = this.repositoriesRetainer.createPaymentRepository().read().count();

        assertEquals(1, paymentRepoSize);
        assertTrue(this.repositoriesRetainer.createPaymentRepository().exists(payment.getId()));
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

        long paymentRepoSize = this.repositoriesRetainer.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);
        assertFalse(this.repositoriesRetainer.createPaymentRepository().exists(payment.getId()));
    }

    @Test
    public void createPayment_unvalidatedPaymentMethod()
    {
        long paymentRepoSize = this.repositoriesRetainer.createPaymentRepository().read().count();

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

        paymentRepoSize = this.repositoriesRetainer.createPaymentRepository().read().count();

        assertEquals(0, paymentRepoSize);
        assertNull(payment);
    }

 */
}
