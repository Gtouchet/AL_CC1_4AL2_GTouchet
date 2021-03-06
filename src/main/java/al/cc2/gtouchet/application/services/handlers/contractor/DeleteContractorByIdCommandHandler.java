package al.cc2.gtouchet.application.services.handlers.contractor;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.DeleteContractorCommand;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class DeleteContractorByIdCommandHandler implements CommandHandler<Boolean, DeleteContractorCommand>
{
    private final Repository<Contractor> contractorRepository;

    public DeleteContractorByIdCommandHandler(Repository contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Boolean handle(DeleteContractorCommand command)
    {
        try {
            this.contractorRepository.remove(command.id);
            return true;
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
