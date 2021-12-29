package al.cc2.gtouchet.infrastructure.repositories.factories;

import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.payment.Payment;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public interface RepositoriesFactory
{
    Repository<Contractor> createContractorRepository();
    Repository<Payment> createPaymentRepository();
    Repository<Project> createProjectRepository();
    Repository<Worker> createWorkerRepository();
}
