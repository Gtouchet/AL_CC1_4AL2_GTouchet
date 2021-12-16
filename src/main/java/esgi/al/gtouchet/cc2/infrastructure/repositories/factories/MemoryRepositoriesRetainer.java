package esgi.al.gtouchet.cc2.infrastructure.repositories.factories;

import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.infrastructure.repositories.MemoryRepository;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
