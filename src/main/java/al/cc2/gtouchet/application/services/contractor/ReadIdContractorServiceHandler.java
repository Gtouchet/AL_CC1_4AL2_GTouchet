package al.cc2.gtouchet.application.services.contractor;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class ReadIdContractorServiceHandler implements ServiceHandler<Contractor, Id>
{
    private final Repository<Contractor> contractorRepository;

    public ReadIdContractorServiceHandler(Repository<Contractor> contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Contractor handle(Id command)
    {
        try {
            return this.contractorRepository.read(command);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
