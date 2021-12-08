package esgi.al.cc1.servicesTests;

import esgi.al.cc1.ServicesAndRepositoriesManager;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class PaymentServiceTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private ServicesAndRepositoriesManager manager;
    private Id contractorId;
    private Id workerId;

    @Before
    public void setup()
    {
        this.manager = new ServicesAndRepositoriesManager();

        this.contractorId = this.manager.contractorService.create(
                "GTouchet1",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );
        this.manager.contractorService.validatePayment(this.contractorId);

        this.workerId = this.manager.workerService.create(
                "GTouchet2",
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        );
    }

    @Test
    public void createPayment()
    {
        long paymentRepoSize = this.manager.paymentService.getRepositorySize();

        assertEquals(0, paymentRepoSize);

        Id paymentId = this.manager.paymentService.create(
                this.contractorId,
                this.workerId,
                1.5,
                "coffee"
        );

        paymentRepoSize = this.manager.paymentService.getRepositorySize();

        assertEquals(1, paymentRepoSize);
        assertTrue(this.manager.paymentService.exists(paymentId));
    }

    @Test
    public void deletePayment()
    {
        Id paymentId = this.manager.paymentService.create(
                this.contractorId,
                this.workerId,
                1.5,
                "coffee"
        );

        this.manager.paymentService.delete(paymentId);

        long paymentRepoSize = this.manager.paymentService.getRepositorySize();

        assertEquals(0, paymentRepoSize);
        assertFalse(this.manager.paymentService.exists(paymentId));
    }

    @Test
    public void createPayment_unvalidatedPaymentMethod()
    {
        long paymentRepoSize = this.manager.paymentService.getRepositorySize();

        assertEquals(0, paymentRepoSize);

        Id unvalidatedContractorId = this.manager.contractorService.create(
                "GTouchet1",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );

        Id paymentId = this.manager.paymentService.create(
                unvalidatedContractorId,
                this.workerId,
                10,
                "Lunch"
        );

        paymentRepoSize = this.manager.paymentService.getRepositorySize();

        assertEquals(0, paymentRepoSize);
        assertFalse(this.manager.paymentService.exists(paymentId));
    }
}
