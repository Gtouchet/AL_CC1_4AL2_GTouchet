package al.cc2.gtouchet.application.services.contractor;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.contractor.dtos.CreateContractorDto;
import al.cc2.gtouchet.domain.builders.ContractorBuilder;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.validators.PasswordFormatException;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class CreateContractorServiceHandler implements ServiceHandler<Contractor, CreateContractorDto>
{
    private final Repository<Contractor> contractorRepository;
    private final Repository<Worker> workerRepository;
    private final PasswordValidator passwordValidator;

    public CreateContractorServiceHandler(
            Repository<Contractor> contractorRepository,
            Repository<Worker> workerRepository,
            PasswordValidator passwordValidator)
    {
        this.contractorRepository = contractorRepository;
        this.workerRepository = workerRepository;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public Contractor handle(CreateContractorDto command)
    {
        if (this.workerRepository.read().anyMatch(worker -> worker.getLogin().equals(command.login)) ||
            this.contractorRepository.read().anyMatch(contractor -> contractor.getLogin().equals(command.login)))
        {
            System.out.println("Error: login already in use");
            return null;
        }

        try {
            this.passwordValidator.validate(command.password);

            Contractor contractor = ContractorBuilder.init(Id.generate(), command.login, Date.now())
                    .setPassword(command.password)
                    .setName(command.name)
                    .setPaymentMethod(command.paymentMethod)
                    .build();

            this.contractorRepository.create(contractor);

            return contractor;

        } catch (PasswordFormatException | NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
