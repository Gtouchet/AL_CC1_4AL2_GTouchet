package esgi.al.gtouchet.cc2.servicesTests;

import esgi.al.gtouchet.cc2.application.services.Services;
import esgi.al.gtouchet.cc2.application.services.ServicesInitializer;
import esgi.al.gtouchet.cc2.application.services.contractor.*;
import esgi.al.gtouchet.cc2.application.services.contractor.dtos.CreateContractorDto;
import esgi.al.gtouchet.cc2.application.services.contractor.dtos.UpdateContractorDto;
import esgi.al.gtouchet.cc2.application.services.worker.CreateWorkerServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.dtos.CreateWorkerDto;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.models.Service;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
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
    private Services services;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();
        this.services = ServicesInitializer.initialize(this.repositoriesFactory);
    }

    @Test
    public void createContractor()
    {
        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(0, contractorRepoSize);

        Contractor contractor = (Contractor) this.services.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
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
        Contractor contractor = (Contractor) this.services.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        assertTrue((boolean) this.services.retrieve(DeleteContractorServiceHandler.class).handle(contractor.getId()));

        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(0, contractorRepoSize);
        assertFalse(this.repositoriesFactory.createContractorRepository().exists(contractor.getId()));
    }

    @Test
    public void createTwoContractors_sameLogin()
    {
        final String sameLogin = "GTouchet";

        Contractor contractor1 = (Contractor) this.services.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        Contractor contractor2 = (Contractor) this.services.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(1, contractorRepoSize);
        assertTrue(this.repositoriesFactory.createContractorRepository().exists(contractor1.getId()));
        assertNull(contractor2);
    }

    @Test
    public void createContractorAndWorker_sameLogin()
    {
        final String sameLogin = "GTouchet";

        Contractor contractor = (Contractor) this.services.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Touchet",
                PaymentMethod.card
        ));

        Worker worker = (Worker) this.services.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                Service.builder,
                91
        ));

        long contractorAndWorkerReposSize =
                this.repositoriesFactory.createContractorRepository().read().count() +
                this.repositoriesFactory.createWorkerRepository().read().count();

        assertEquals(1, contractorAndWorkerReposSize);
        assertTrue(this.repositoriesFactory.createContractorRepository().exists(contractor.getId()));
        assertNull(worker);
    }

    @Test
    public void updateContractor()
    {
        Contractor contractor = (Contractor) this.services.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        Password newPassword = Password.of("newPass123??");
        String newName = "Robert";
        PaymentMethod newPaymentMethod = PaymentMethod.paypal;

        Contractor updatedContractor = (Contractor) this.services.retrieve(UpdateContractorServiceHandler.class).handle(new UpdateContractorDto(
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
        Contractor contractor = (Contractor) this.services.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        assertFalse(contractor.isPaymentValidated());

        assertTrue((boolean) this.services.retrieve(ValidatePaymentServiceHandler.class).handle(contractor.getId()));

        contractor = (Contractor) this.services.retrieve(ReadIdContractorServiceHandler.class).handle(contractor.getId());
        assertTrue(contractor.isPaymentValidated());
    }

    @Test
    public void unrecognizedPaymentMethod()
    {
        exception.expect(IllegalArgumentException.class);

        this.services.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.valueOf("€4$H")
        ));
    }
}
