package esgi.al.gtouchet.cc2.application;

import esgi.al.gtouchet.cc2.application.contractorServices.ContractorServicesFactory;
import esgi.al.gtouchet.cc2.application.paymentServices.PaymentServicesFactory;

public class ServicesFactory
{
    public ContractorServicesFactory createContractorServicesFactory()
    {
        return new ContractorServicesFactory();
    }

    public PaymentServicesFactory createPaymentServicesFactory()
    {
        return new PaymentServicesFactory();
    }

    // todo implements all services factory
}
