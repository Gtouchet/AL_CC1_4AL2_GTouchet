package esgi.al.gtouchet.cc2.application.contractorServices.delete;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
