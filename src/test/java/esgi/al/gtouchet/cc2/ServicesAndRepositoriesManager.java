package esgi.al.gtouchet.cc2;

import esgi.al.gtouchet.cc2.application.ContractorServiceImpl;
import esgi.al.gtouchet.cc2.application.PaymentServiceImpl;
import esgi.al.gtouchet.cc2.application.ProjectServiceImpl;
import esgi.al.gtouchet.cc2.application.WorkerServiceImpl;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.MemoryRepositoryImpl;

/**
 * For test purposes only
 */
public class ServicesAndRepositoriesManager
{
    public final MemoryRepositoryImpl<Contractor> contractorIMR;
    public final MemoryRepositoryImpl<Payment> paymentIMR;
    public final MemoryRepositoryImpl<Project> projectIMR;
    public final MemoryRepositoryImpl<Worker> workerIMR;

    public final PaymentMethodValidatorApi paymentMethodValidatorApi;
    public final PasswordValidator passwordValidator;

    public final ContractorServiceImpl contractorService;
    public final PaymentServiceImpl paymentService;
    public final ProjectServiceImpl projectService;
    public final WorkerServiceImpl workerService;

    public ServicesAndRepositoriesManager()
    {
        this.contractorIMR = new MemoryRepositoryImpl<>();
        this.paymentIMR = new MemoryRepositoryImpl<>();
        this.projectIMR = new MemoryRepositoryImpl<>();
        this.workerIMR = new MemoryRepositoryImpl<>();

        this.paymentMethodValidatorApi = new PaymentMethodValidatorApi();
        this.passwordValidator = new PasswordValidator();

        this.contractorService = new ContractorServiceImpl(
                this.contractorIMR,
                this.workerIMR,
                this.paymentMethodValidatorApi,
                this.passwordValidator
        );
        this.paymentService = new PaymentServiceImpl(
                this.paymentIMR,
                this.contractorIMR,
                this.workerIMR
        );
        this.projectService = new ProjectServiceImpl(
                this.projectIMR,
                this.contractorIMR,
                this.workerIMR
        );
        this.workerService = new WorkerServiceImpl(
                this.workerIMR,
                this.contractorIMR,
                this.projectIMR,
                this.passwordValidator
        );
    }
}
