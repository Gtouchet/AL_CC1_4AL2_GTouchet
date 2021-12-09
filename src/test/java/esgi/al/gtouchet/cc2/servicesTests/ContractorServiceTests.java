package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.ServicesAndRepositoriesManager;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.models.Service;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ContractorServiceTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private ServicesAndRepositoriesManager manager;

    @Before
    public void setup()
    {
        this.manager = new ServicesAndRepositoriesManager();
    }

    @Test
    public void createContractor()
    {
        long contractorRepoSize = this.manager.contractorService.getRepositorySize();

        assertEquals(0, contractorRepoSize);

        Id contractorId = this.manager.contractorService.create(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );

        contractorRepoSize = this.manager.contractorService.getRepositorySize();

        assertEquals(1, contractorRepoSize);
        assertTrue(this.manager.contractorService.exists(contractorId));
    }

    @Test
    public void deleteContractor()
    {
        Id contractorId = this.manager.contractorService.create(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );

        this.manager.contractorService.delete(contractorId);

        long contractorRepoSize = this.manager.contractorService.getRepositorySize();

        assertEquals(0, contractorRepoSize);
        assertFalse(this.manager.contractorService.exists(contractorId));
    }

    @Test
    public void createTwoContractors_sameLogin()
    {
        final String login = "GTouchet";
        long contractorRepoSize;

        Id contractorId1 = this.manager.contractorService.create(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );

        Id contractorId2 = this.manager.contractorService.create(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );

        contractorRepoSize = this.manager.contractorService.getRepositorySize();

        assertEquals(1, contractorRepoSize);
        assertTrue(this.manager.contractorService.exists(contractorId1));
        assertFalse(this.manager.contractorService.exists(contractorId2));
    }

    @Test
    public void createContractorAndWorker_sameLogin()
    {
        final String login = "GTouchet";
        long contractorAndWorkerReposSize;

        Id contractorId = this.manager.contractorService.create(
                login,
                Password.of("ABcd1234!"),
                "Touchet",
                PaymentMethod.card
        );

        Id workerId = this.manager.workerService.create(
                login,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        );

        contractorAndWorkerReposSize =
                this.manager.contractorService.getRepositorySize() +
                this.manager.workerService.getRepositorySize();

        assertEquals(1, contractorAndWorkerReposSize);
        assertTrue(this.manager.contractorService.exists(contractorId));
        assertFalse(this.manager.workerService.exists(workerId));
    }

    @Test
    public void updateContractor() throws EntityNotFoundException
    {
        Id contractorId = this.manager.contractorService.create(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );

        Contractor originalContractor = this.manager.contractorIMR.read(contractorId);

        Password newPassword = Password.of("newPass123??");
        String newName = "Robert";
        PaymentMethod newPaymentMethod = PaymentMethod.paypal;

        this.manager.contractorService.update(contractorId,
                newPassword,
                newName,
                newPaymentMethod
        );

        Contractor updatedContractor = this.manager.contractorIMR.read(contractorId);

        assertNotSame(originalContractor, updatedContractor);
        assertEquals(originalContractor.getId(), updatedContractor.getId());
        assertEquals(updatedContractor.getPassword(), newPassword);
        assertEquals(updatedContractor.getName(), newName);
        assertEquals(updatedContractor.getPaymentMethod(), newPaymentMethod);
    }

    @Test
    public void validatePaymentMethod() throws EntityNotFoundException
    {
        Id contractorId = this.manager.contractorService.create(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        );

        Contractor contractor = this.manager.contractorIMR.read(contractorId);
        assertFalse(contractor.isPaymentValidated());

        this.manager.contractorService.validatePayment(contractorId);

        contractor = this.manager.contractorIMR.read(contractorId);
        assertTrue(contractor.isPaymentValidated());
    }

    @Test
    public void unrecognizedPaymentMethod()
    {
        exception.expect(IllegalArgumentException.class);

        this.manager.contractorService.create(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.valueOf("CA$H")
        );
    }
}
