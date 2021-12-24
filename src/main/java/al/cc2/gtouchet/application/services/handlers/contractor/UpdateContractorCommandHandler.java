package al.cc2.gtouchet.application.services.handlers.contractor;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.UpdateContractorCommand;
import al.cc2.gtouchet.domain.builders.ContractorBuilder;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.validators.PasswordFormatException;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class UpdateContractorCommandHandler implements CommandHandler<Contractor, UpdateContractorCommand>
{
    private final Repository<Contractor> contractorRepository;
    private final PasswordValidator passwordValidator;

    public UpdateContractorCommandHandler(
            Repository<Contractor> contractorRepository,
            PasswordValidator passwordValidator)
    {
        this.contractorRepository = contractorRepository;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public Contractor handle(UpdateContractorCommand command)
    {
        try {
            Contractor contractor = this.contractorRepository.read(command.id);

            this.passwordValidator.validate(command.password);

            contractor = ContractorBuilder.init(contractor)
                    .setPassword(command.password)
                    .setName(command.name)
                    .setPaymentMethod(command.paymentMethod)
                    .build();

            this.contractorRepository.update(command.id, contractor);

            return contractor;

        } catch (EntityNotFoundException | PasswordFormatException | NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
