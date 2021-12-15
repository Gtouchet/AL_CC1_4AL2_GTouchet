package esgi.al.gtouchet.cc2.infrastructure.repositories;

import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.models.Worker;

public interface RepositoriesFactory
{
    Repository<Contractor> createContractorRepository();
    Repository<Payment> createPaymentRepository();
    Repository<Project> createProjectRepository();
    Repository<Worker> createWorkerRepository();
}
