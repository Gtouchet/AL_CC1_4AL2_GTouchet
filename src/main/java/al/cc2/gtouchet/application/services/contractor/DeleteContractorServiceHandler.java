package al.cc2.gtouchet.application.services.contractor;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class DeleteContractorServiceHandler implements ServiceHandler<Boolean, Id>
{
    private final Repository<Contractor> contractorRepository;

    public DeleteContractorServiceHandler(Repository<Contractor> contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Boolean handle(Id command)
    {
        try {
            this.contractorRepository.remove(command);
            return true;
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
