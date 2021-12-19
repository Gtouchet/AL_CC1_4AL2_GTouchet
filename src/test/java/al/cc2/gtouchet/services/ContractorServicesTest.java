package al.cc2.gtouchet.services;

import al.cc2.gtouchet.application.services.ServicesContainer;
import al.cc2.gtouchet.application.services.contractor.*;
import al.cc2.gtouchet.application.services.contractor.dtos.CreateContractorDto;
import al.cc2.gtouchet.application.services.contractor.dtos.UpdateContractorDto;
import al.cc2.gtouchet.application.services.worker.CreateWorkerServiceHandler;
import al.cc2.gtouchet.application.services.worker.dtos.CreateWorkerDto;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.models.Service;
import al.cc2.gtouchet.domain.models.Worker;
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

public class ContractorServicesTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private RepositoriesFactory repositoriesFactory;
    private ServicesContainer servicesContainer;

    @Before
    public void setup()
    {
        this.repositoriesFactory = new MemoryRepositoriesRetainer();
        this.servicesContainer = ServicesContainer.initialize(
                this.repositoriesFactory,
                new PasswordValidator(),
                new PaymentMethodValidatorApi()
        );
    }

    @Test
    public void createContractor()
    {
        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(0, contractorRepoSize);

        Contractor contractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
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
        Contractor contractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        assertTrue((boolean) this.servicesContainer.retrieve(DeleteContractorServiceHandler.class).handle(contractor.getId()));

        long contractorRepoSize = this.repositoriesFactory.createContractorRepository().read().count();

        assertEquals(0, contractorRepoSize);
        assertFalse(this.repositoriesFactory.createContractorRepository().exists(contractor.getId()));
    }

    @Test
    public void createTwoContractors_sameLogin()
    {
        final String sameLogin = "GTouchet";

        Contractor contractor1 = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        Contractor contractor2 = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
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

        Contractor contractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                sameLogin,
                Password.of("ABcd1234!"),
                "Touchet",
                PaymentMethod.card
        ));

        Worker worker = (Worker) this.servicesContainer.retrieve(CreateWorkerServiceHandler.class).handle(new CreateWorkerDto(
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
        Contractor originalContractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        Password newPassword = Password.of("newPass123??");
        String newName = "Robert";
        PaymentMethod newPaymentMethod = PaymentMethod.paypal;

        Contractor updatedContractor = (Contractor) this.servicesContainer.retrieve(UpdateContractorServiceHandler.class).handle(new UpdateContractorDto(
                originalContractor.getId(),
                newPassword,
                newName,
                newPaymentMethod
        ));

        assertNotSame(originalContractor, updatedContractor);
        assertEquals(originalContractor.getId(), updatedContractor.getId());
        assertEquals(updatedContractor.getPassword(), newPassword);
        assertEquals(updatedContractor.getName(), newName);
        assertEquals(updatedContractor.getPaymentMethod(), newPaymentMethod);
    }

    @Test
    public void validatePaymentMethod()
    {
        Contractor contractor = (Contractor) this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.card
        ));

        assertFalse(contractor.isPaymentValidated());

        assertTrue((boolean) this.servicesContainer.retrieve(ValidatePaymentServiceHandler.class).handle(contractor.getId()));

        contractor = (Contractor) this.servicesContainer.retrieve(ReadIdContractorServiceHandler.class).handle(contractor.getId());
        assertTrue(contractor.isPaymentValidated());
    }

    @Test
    public void unrecognizedPaymentMethod()
    {
        exception.expect(IllegalArgumentException.class);

        this.servicesContainer.retrieve(CreateContractorServiceHandler.class).handle(new CreateContractorDto(
                "GTouchet",
                Password.of("ABcd1234!"),
                "Guillaume",
                PaymentMethod.valueOf("€4$H")
        ));
    }
}
