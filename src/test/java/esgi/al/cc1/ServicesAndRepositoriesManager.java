package esgi.al.cc1;

import esgi.al.cc1.application.*;
import esgi.al.cc1.inMemoryRepositories.ContractorInMemoryRepository;
import esgi.al.cc1.inMemoryRepositories.PaymentInMemoryRepository;
import esgi.al.cc1.inMemoryRepositories.ProjectInMemoryRepository;
import esgi.al.cc1.inMemoryRepositories.WorkerInMemoryRepository;

public abstract class ServicesAndRepositoriesManager
{
    protected ContractorInMemoryRepository contractorIMR;
    protected PaymentInMemoryRepository paymentIMR;
    protected ProjectInMemoryRepository projectIMR;
    protected WorkerInMemoryRepository workerIMR;

    protected ContractorService contractorService;
    protected PaymentService paymentService;
    protected ProjectService projectService;
    protected WorkerService workerService;

    // Resets the service and in memory repositories before each test
    protected void resetServiceAndRepositories()
    {
        this.contractorIMR = new ContractorInMemoryRepository();
        this.paymentIMR = new PaymentInMemoryRepository();
        this.projectIMR = new ProjectInMemoryRepository();
        this.workerIMR = new WorkerInMemoryRepository();

        this.contractorService = new ContractorServiceImpl(
                this.contractorIMR,
                this.workerIMR
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
                this.projectIMR
        );
    }
}
