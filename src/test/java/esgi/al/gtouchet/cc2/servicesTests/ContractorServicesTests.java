package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.application.services.factories.MemoryServicesFactory;
import esgi.al.gtouchet.cc2.application.services.factories.ServicesFactory;
import esgi.al.gtouchet.cc2.application.services.contractor.ContractorServicesFactory;
import esgi.al.gtouchet.cc2.application.services.contractor.create.CreateContractorDto;
import esgi.al.gtouchet.cc2.application.services.contractor.update.UpdateContractorDto;
import esgi.al.gtouchet.cc2.application.services.worker.WorkerServicesFactory;
import esgi.al.gtouchet.cc2.application.services.worker.create.CreateWorkerDto;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.models.Service;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.MemoryRepositoriesRetainer;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ContractorServicesTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private RepositoriesFactory repositoriesFactory;

    private ContractorServicesFactory contractorServicesFactory;
    private WorkerServicesFactory workerServicesFactory;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();

        ServicesFactory servicesFactory = new MemoryServicesFactory(this.repositoriesFactory);
        this.contractorServicesFactory = servicesFactory.createContractorServicesFactory();
        this.workerServicesFactory = servicesFactory.createWorkerServicesFactory();
    }

    @Test
    public void createContractor()
    {
        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(0, contractorRepoSize);

        Contractor contractor = this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(1, contractorRepoSize);
        assertTrue(this.repositoriesFactory.createContractorRepository().exists(contractor.getId()));
    }

    @Test
    public void deleteContractor()
    {
        Contractor contractor = this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        this.contractorServicesFactory.getDeleteContractorService().handle(contractor.getId());

        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(0, contractorRepoSize);
        assertFalse(this.repositoriesFactory.createContractorRepository().exists(contractor.getId()));
    }

    @Test
    public void createTwoContractors_sameLogin()
    {
        final String sameLogin = "GTouchet";

        Contractor contractor1 = this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        Contractor contractor2 = this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(1, contractorRepoSize);
        assertTrue(this.repositoriesFactory.createContractorRepository().exists(contractor1.getId()));
    }

    @Test
    public void createContractorAndWorker_sameLogin()
    {
        final String sameLogin = "GTouchet";
        long contractorAndWorkerReposSize;

        Contractor contractor = this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Touchet",
                PaymentMethod.card
        ));

        Worker worker = this.workerServicesFactory.getCreateWorkerHandler().handle(new CreateWorkerDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        contractorAndWorkerReposSize =
                this.repositoriesFactory.createContractorRepository().read().count() +
                this.repositoriesFactory.createWorkerRepository().read().count();

        assertEquals(1, contractorAndWorkerReposSize);
        assertTrue(this.repositoriesFactory.createContractorRepository().exists(contractor.getId()));
        assertNull(worker);
    }

    @Test
    public void updateContractor() throws EntityNotFoundException
    {
        Contractor contractor = this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        Password newPassword = Password.of("newPass123??");
        String newName = "Robert";
        PaymentMethod newPaymentMethod = PaymentMethod.paypal;

        Contractor updatedContractor = this.contractorServicesFactory.getUpdateContractorHandler().handle(new UpdateContractorDto(
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
        Contractor contractor = this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        assertFalse(contractor.isPaymentValidated());

        assertTrue(this.contractorServicesFactory.getValidatePaymentService().handle(contractor.getId()));

        contractor = this.contractorServicesFactory.getReadIdContractorHandler().handle(contractor.getId());
        assertTrue(contractor.isPaymentValidated());
    }

    @Test
    public void unrecognizedPaymentMethod()
    {
        exception.expect(IllegalArgumentException.class);

        this.contractorServicesFactory.getCreateContractorHandler().handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.valueOf("€4$H")
        ));
    }
}
