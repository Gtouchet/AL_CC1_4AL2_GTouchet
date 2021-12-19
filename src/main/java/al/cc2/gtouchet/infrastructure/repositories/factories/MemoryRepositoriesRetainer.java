package al.cc2.gtouchet.infrastructure.repositories.factories;

import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.infrastructure.repositories.MemoryRepository;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class MemoryRepositoriesRetainer implements RepositoriesFactory
{
    private final Repository<Contractor> contractorRepository;
    private final Repository<Payment> paymentRepository;
    private final Repository<Project> projectRepository;
    private final Repository<Worker> workerRepository;

    public MemoryRepositoriesRetainer()
    {
        this.contractorRepository = new MemoryRepository<>();
        this.paymentRepository = new MemoryRepository<>();
        this.projectRepository = new MemoryRepository<>();
        this.workerRepository = new MemoryRepository<>();
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
