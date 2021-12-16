package esgi.al.gtouchet.cc2.application.services.contractor;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.create.CreateContractorDto;
import esgi.al.gtouchet.cc2.application.services.contractor.create.CreateContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.delete.DeleteContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.read.ReadAllContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.read.ReadIdContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.update.UpdateContractorDto;
import esgi.al.gtouchet.cc2.application.services.contractor.update.UpdateContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.validatePayment.ValidatePaymentServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;

import java.util.List;

public class ContractorServicesFactory
{
    private final RepositoriesFactory repositoriesFactory;

    public ContractorServicesFactory(RepositoriesFactory repositoriesFactory)
    {
        this.repositoriesFactory = repositoriesFactory;
    }

    public ServiceHandler<Contractor, CreateContractorDto> getCreateContractorHandler()
    {
        return new CreateContractorServiceHandler(
                this.repositoriesFactory.createContractorRepository(),
                this.repositoriesFactory.createWorkerRepository(),
                new PasswordValidator()
        );
    }

    public ServiceHandler<List<Contractor>, Void> getReadAllContractorHandler()
    {
        return new ReadAllContractorServiceHandler(
                this.repositoriesFactory.createContractorRepository()
        );
    }

    public ServiceHandler<Contractor, Id> getReadIdContractorHandler()
    {
        return new ReadIdContractorServiceHandler(
                this.repositoriesFactory.createContractorRepository()
        );
    }

    public ServiceHandler<Contractor, UpdateContractorDto> getUpdateContractorHandler()
    {
        return new UpdateContractorServiceHandler(
                this.repositoriesFactory.createContractorRepository(),
                new PasswordValidator()
        );
    }

    public ServiceHandler<Boolean, Id> getDeleteContractorService()
    {
        return new DeleteContractorServiceHandler(
                this.repositoriesFactory.createContractorRepository()
        );
    }

    public ServiceHandler<Boolean, Id> getValidatePaymentService()
    {
        return new ValidatePaymentServiceHandler(
                this.repositoriesFactory.createContractorRepository(),
                new PaymentMethodValidatorApi()
        );
    }
}
