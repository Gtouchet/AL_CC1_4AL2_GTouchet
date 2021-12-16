package esgi.al.gtouchet.cc2.application.services.contractor.create;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.builders.ContractorBuilder;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordFormatException;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Date;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
