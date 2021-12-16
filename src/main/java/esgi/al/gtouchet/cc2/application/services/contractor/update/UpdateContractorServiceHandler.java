package esgi.al.gtouchet.cc2.application.services.contractor.update;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.builders.ContractorBuilder;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.validators.PasswordFormatException;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

public class UpdateContractorServiceHandler implements ServiceHandler<Contractor, UpdateContractorDto>
{
    private final Repository<Contractor> contractorRepository;
    private final PasswordValidator passwordValidator;

    public UpdateContractorServiceHandler(
            Repository<Contractor> contractorRepository,
            PasswordValidator passwordValidator)
    {
        this.contractorRepository = contractorRepository;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public Contractor handle(UpdateContractorDto command)
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
