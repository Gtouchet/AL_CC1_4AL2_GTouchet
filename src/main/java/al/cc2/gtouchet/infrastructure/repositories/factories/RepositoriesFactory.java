package al.cc2.gtouchet.infrastructure.repositories.factories;

import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public interface RepositoriesFactory
{
    Repository<Contractor> createContractorRepository();
    Repository<Payment> createPaymentRepository();
    Repository<Project> createProjectRepository();
    Repository<Worker> createWorkerRepository();
}
