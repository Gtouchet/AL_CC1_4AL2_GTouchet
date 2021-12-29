package al.cc2.gtouchet.infrastructure.repositories.factories;

import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.payment.Payment;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.infrastructure.repositories.InMemoryRepository;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class MemoryRepositoriesRetainer implements RepositoriesFactory
{
    private final Repository<Contractor> contractorRepository;
    private final Repository<Payment> paymentRepository;
    private final Repository<Project> projectRepository;
    private final Repository<Worker> workerRepository;

    public MemoryRepositoriesRetainer()
    {
        this.contractorRepository = new InMemoryRepository<>();
        this.paymentRepository = new InMemoryRepository<>();
        this.projectRepository = new InMemoryRepository<>();
        this.workerRepository = new InMemoryRepository<>();
    }

    @Override
    public Repository<Contractor> createContractorRepository()
    {
        return this.contractorRepository;
    }

    @Override
    public Repository<Payment> createPaymentRepository()
    {
        return this.paymentRepository;
    }

    @Override
    public Repository<Project> createProjectRepository()
    {
        return this.projectRepository;
    }

    @Override
    public Repository<Worker> createWorkerRepository()
    {
        return this.workerRepository;
    }
}
