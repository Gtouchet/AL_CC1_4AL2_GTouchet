package esgi.al.cc1;

import esgi.al.cc1.application.ContractorServiceImpl;
import esgi.al.cc1.application.PaymentServiceImpl;
import esgi.al.cc1.application.ProjectServiceImpl;
import esgi.al.cc1.application.WorkerServiceImpl;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.apis.PaymentMethodValidatorApi;

public class ServicesAndRepositoriesManager
{
    public final InMemoryRepository<Contractor> contractorIMR;
    public final InMemoryRepository<Payment> paymentIMR;
    public final InMemoryRepository<Project> serviceIMR;
    public final InMemoryRepository<Worker> workerIMR;

    public final ContractorServiceImpl contractorService;
    public final PaymentServiceImpl paymentService;
    public final ProjectServiceImpl projectService;
    public final WorkerServiceImpl workerService;

    public ServicesAndRepositoriesManager()
    {
        this.contractorIMR = new InMemoryRepository<>();
        this.paymentIMR = new InMemoryRepository<>();
        this.serviceIMR = new InMemoryRepository<>();
        this.workerIMR = new InMemoryRepository<>();

        this.contractorService = new ContractorServiceImpl(
                this.contractorIMR,
                this.workerIMR,
                new PaymentMethodValidatorApi()
        );
        this.paymentService = new PaymentServiceImpl(
                this.paymentIMR,
                this.contractorIMR,
                this.workerIMR
        );
        this.projectService = new ProjectServiceImpl(
                this.serviceIMR,
                this.contractorIMR,
                this.workerIMR
        );
        this.workerService = new WorkerServiceImpl(
                this.workerIMR,
                this.contractorIMR,
                this.serviceIMR
        );
    }
}
