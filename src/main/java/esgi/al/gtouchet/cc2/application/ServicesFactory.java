package esgi.al.gtouchet.cc2.application;

import esgi.al.gtouchet.cc2.application.contractorServices.ContractorServicesFactory;

public class ServicesFactory
{
    public ContractorServicesFactory createContractorServicesFactory()
    {
        return new ContractorServicesFactory();
    }
}
