package esgi.al.gtouchet.cc2.application.services.factories;

import esgi.al.gtouchet.cc2.application.services.contractor.ContractorServicesFactory;
import esgi.al.gtouchet.cc2.application.services.payment.PaymentServicesFactory;
import esgi.al.gtouchet.cc2.application.services.project.ProjectServicesFactory;
import esgi.al.gtouchet.cc2.application.services.worker.WorkerServicesFactory;

public interface ServicesFactory
{
    ContractorServicesFactory createContractorServicesFactory();
    PaymentServicesFactory createPaymentServicesFactory();
    ProjectServicesFactory createProjectServicesFactory();
    WorkerServicesFactory createWorkerServicesFactory();
}
