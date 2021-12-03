package esgi.al.cc1.serviceTests;

import esgi.al.cc1.ServicesAndRepositoriesManager;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import esgi.al.cc1.infrastructure.repositories.EntityNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ContractorServiceTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private ServicesAndRepositoriesManager manager;

    @Test
    public void createContractor()
    {
        this.manager = new ServicesAndRepositoriesManager();
        long contractorRepoSize;

        contractorRepoSize = this.manager.contractorService.getRepositorySize();
        assertEquals(0, contractorRepoSize);

        Id contractorId = this.manager.contractorService.create(
                "GTouchet",
                Password.of("pass123"),
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
        this.manager = new ServicesAndRepositoriesManager();
        long contractorRepoSize;

        Id contractorId = this.manager.contractorService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                PaymentMethod.card
        );

        this.manager.contractorService.delete(contractorId);

        contractorRepoSize = this.manager.contractorService.getRepositorySize();
        assertEquals(0, contractorRepoSize);
        assertFalse(this.manager.contractorService.exists(contractorId));
    }

    @Test
    public void createTwoContractors_sameLogin()
    {
        this.manager = new ServicesAndRepositoriesManager();
        final String login = "GTouchet";
        long contractorRepoSize;

        Id contractorId1 = this.manager.contractorService.create(
                login,
                Password.of("pass123"),
                "Guillaume",
                PaymentMethod.card
        );

        Id contractorId2 = this.manager.contractorService.create(
                login,
                Password.of("pass123"),
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
        this.manager = new ServicesAndRepositoriesManager();
        final String login = "GTouchet";
        long contractorAndWorkerReposSize;

        Id contractorId = this.manager.contractorService.create(
                login,
                Password.of("123pass"),
                "Touchet",
                PaymentMethod.card
        );

        Id workerId = this.manager.workerService.create(
                login,
                Password.of("pass123"),
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
        this.manager = new ServicesAndRepositoriesManager();

        Id contractorId = this.manager.contractorService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                PaymentMethod.card
        );

        final Password newPassword = Password.of("456pass");
        final String newName = "Robert";
        final PaymentMethod newPaymentMethod = PaymentMethod.paypal;

        this.manager.contractorService.update(contractorId,
                newPassword,
                newName,
                newPaymentMethod
        );

        Contractor updatedContractor = this.manager.contractorIMR.read(contractorId);
        assertEquals(newPassword, updatedContractor.getPassword());
        assertEquals(newName, updatedContractor.getName());
        assertEquals(newPaymentMethod, updatedContractor.getPaymentMethod());

        assertEquals(contractorId, updatedContractor.getId());
    }

    @Test
    public void validatePaymentMethod() throws EntityNotFoundException
    {
        this.manager = new ServicesAndRepositoriesManager();

        Id contractorId = this.manager.contractorService.create(
                "GTouchet",
                Password.of("pass123"),
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
        this.manager = new ServicesAndRepositoriesManager();
        exception.expect(IllegalArgumentException.class);

        this.manager.contractorService.create(
                "GTouchet",
                Password.of("pass123"),
                "Guillaume",
                PaymentMethod.valueOf("cash")
        );
    }

    @Test
    public void invalidPassword()
    {
        // Todo, not implemented yet :s
    }
}
