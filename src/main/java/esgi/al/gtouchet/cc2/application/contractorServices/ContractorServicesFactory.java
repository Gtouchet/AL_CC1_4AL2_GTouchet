package esgi.al.gtouchet.cc2.application.contractorServices;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.create.CreateContractorDto;
import esgi.al.gtouchet.cc2.application.contractorServices.create.CreateContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.delete.DeleteContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.read.ReadContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.update.UpdateContractorDto;
import esgi.al.gtouchet.cc2.application.contractorServices.update.UpdateContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.validatePayment.ValidatePaymentServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.DataRepositoriesFactory;

import java.util.List;

public class ContractorServicesFactory
{
    public ServiceHandler<Contractor, CreateContractorDto> createContractorHandler()
    {
        return new CreateContractorServiceHandler(
                new DataRepositoriesFactory().createContractorRepository(),
                new DataRepositoriesFactory().createWorkerRepository(),
                new PasswordValidator()
        );
    }

    public ServiceHandler<List<Contractor>, Id> readContractorHandler()
    {
        return new ReadContractorServiceHandler(
                new DataRepositoriesFactory().createContractorRepository()
        );
    }

    public ServiceHandler<Contractor, UpdateContractorDto> updateContractorHandler()
    {
        return new UpdateContractorServiceHandler(
                new DataRepositoriesFactory().createContractorRepository(),
                new PasswordValidator()
        );
    }

    public ServiceHandler<Boolean, Id> deleteContractorService()
    {
        return new DeleteContractorServiceHandler(
                new DataRepositoriesFactory().createContractorRepository()
        );
    }

    public ServiceHandler<Boolean, Id> validatePaymentService()
    {
        return new ValidatePaymentServiceHandler(
                new DataRepositoriesFactory().createContractorRepository(),
                new PaymentMethodValidatorApi()
        );
    }
}
