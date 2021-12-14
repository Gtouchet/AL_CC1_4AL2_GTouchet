package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.TestsManager;
import esgi.al.gtouchet.cc2.application.contractorServices.create.CreateContractorDto;
import esgi.al.gtouchet.cc2.application.contractorServices.update.UpdateContractorDto;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
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

    private TestsManager testsManager;

    @Before
    public void setup()
    {
        this.testsManager = new TestsManager();
    }

    @Test
    public void createContractor()
    {
        long contractorRepoSize = this.testsManager.contractorIMR.read().count();

        assertEquals(0, contractorRepoSize);

        Contractor contractor = this.testsManager.createContractorHandler.handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        contractorRepoSize = this.testsManager.contractorIMR.read().count();

        assertEquals(1, contractorRepoSize);
        assertTrue(this.testsManager.contractorIMR.exists(contractor.getId()));
    }

    @Test
    public void deleteContractor()
    {
        Contractor contractor = this.testsManager.createContractorHandler.handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        this.testsManager.deleteContractorHandler.handle(contractor.getId());

        long contractorRepoSize = this.testsManager.contractorIMR.read().count();

        assertEquals(0, contractorRepoSize);
        assertFalse(this.testsManager.contractorIMR.exists(contractor.getId()));
    }

    @Test
    public void createTwoContractors_sameLogin()
    {
        final String sameLogin = "GTouchet";

        Contractor contractor1 = this.testsManager.createContractorHandler.handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        Contractor contractor2 = this.testsManager.createContractorHandler.handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        long contractorRepoSize = this.testsManager.contractorIMR.read().count();

        assertEquals(1, contractorRepoSize);
        assertTrue(this.testsManager.contractorIMR.exists(contractor1.getId()));
    }
/*
    @Test // TODO quand t'auras implémenté les handlers des workers
    public void createContractorAndWorker_sameLogin()
    {
        final String sameLogin = "GTouchet";
        long contractorAndWorkerReposSize;

        Contractor contractor = this.testsManager.createContractorHandler.handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Touchet",
                PaymentMethod.card
        ));

        Worker worker = this.testsManager.FAIL.handle(
                sameLogin,
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
*/
    @Test
    public void updateContractor() throws EntityNotFoundException
    {
        Contractor contractor = this.testsManager.createContractorHandler.handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        Password newPassword = Password.of("newPass123??");
        String newName = "Robert";
        PaymentMethod newPaymentMethod = PaymentMethod.paypal;

        Contractor updatedContractor = this.testsManager.updateContractorHandler.handle(new UpdateContractorDto(
                contractor.getId(),
                newPassword,
                newName,
                newPaymentMethod
        ));

        assertNotSame(contractor, updatedContractor);
        assertEquals(contractor.getId(), updatedContractor.getId());
        assertEquals(updatedContractor.getPassword(), newPassword);
        assertEquals(updatedContractor.getName(), newName);
        assertEquals(updatedContractor.getPaymentMethod(), newPaymentMethod);
    }

    @Test
    public void validatePaymentMethod()
    {
        Contractor contractor = this.testsManager.createContractorHandler.handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        assertFalse(contractor.isPaymentValidated());

        assertTrue(this.testsManager.validatePaymentHandler.handle(contractor.getId()));

        contractor = this.testsManager.readIdContractorHandler.handle(contractor.getId());
        assertTrue(contractor.isPaymentValidated());
    }

    @Test
    public void unrecognizedPaymentMethod()
    {
        exception.expect(IllegalArgumentException.class);

        this.testsManager.createContractorHandler.handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.valueOf("€4$H")
        ));
    }
}
