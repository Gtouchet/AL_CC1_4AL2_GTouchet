package esgi.al.gtouchet.cc2;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.create.CreateContractorDto;
import esgi.al.gtouchet.cc2.application.contractorServices.create.CreateContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.delete.DeleteContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.read.ReadAllContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.read.ReadIdContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.update.UpdateContractorDto;
import esgi.al.gtouchet.cc2.application.contractorServices.update.UpdateContractorServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.validatePayment.ValidatePaymentServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.MemoryRepository;

import java.util.List;

public class TestsManager
{
    public final ServiceHandler<Contractor, CreateContractorDto> createContractorHandler;
    public final ServiceHandler<List<Contractor>, Void> readAllContractorHandler;
    public final ServiceHandler<Contractor, Id> readIdContractorHandler;
    public final ServiceHandler<Contractor, UpdateContractorDto> updateContractorHandler;
    public final ServiceHandler<Boolean, Id> deleteContractorHandler;
    public final ServiceHandler<Boolean, Id> validatePaymentHandler;

    public final MemoryRepository<Contractor> contractorIMR;
    public final MemoryRepository<Payment> paymentIMR;
    public final MemoryRepository<Project> projectIMR;
    public final MemoryRepository<Worker> workerIMR;

    public TestsManager()
    {
        this.contractorIMR = new MemoryRepository<>();
        this.paymentIMR = new MemoryRepository<>();
        this.projectIMR = new MemoryRepository<>();
        this.workerIMR = new MemoryRepository<>();

        this.createContractorHandler = new CreateContractorServiceHandler(
                this.contractorIMR,
                this.workerIMR,
                new PasswordValidator()
        );

        this.readAllContractorHandler = new ReadAllContractorServiceHandler(
                this.contractorIMR
        );

        this.readIdContractorHandler = new ReadIdContractorServiceHandler(
                this.contractorIMR
        );

        this.updateContractorHandler = new UpdateContractorServiceHandler(
                this.contractorIMR,
                new PasswordValidator()
        );

        this.deleteContractorHandler = new DeleteContractorServiceHandler(
                this.contractorIMR
        );

        this.validatePaymentHandler = new ValidatePaymentServiceHandler(
                this.contractorIMR,
                new PaymentMethodValidatorApi()
        );
    }
}
