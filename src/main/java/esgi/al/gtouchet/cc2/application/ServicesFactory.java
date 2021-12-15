package esgi.al.gtouchet.cc2.application;

import esgi.al.gtouchet.cc2.application.contractorServices.ContractorServicesFactory;
import esgi.al.gtouchet.cc2.application.paymentServices.PaymentServicesFactory;
import esgi.al.gtouchet.cc2.application.projectServices.ProjectServicesFactory;
import esgi.al.gtouchet.cc2.application.workerServices.WorkerServicesFactory;

public interface ServicesFactory
{
    ContractorServicesFactory createContractorServicesFactory();
    PaymentServicesFactory createPaymentServicesFactory();
    ProjectServicesFactory createProjectServicesFactory();
    WorkerServicesFactory createWorkerServicesFactory();
}
