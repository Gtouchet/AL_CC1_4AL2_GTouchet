package al.cc2.gtouchet.application.services.handlers.worker;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.worker.CreateWorkerCommand;
import al.cc2.gtouchet.domain.builders.WorkerBuilder;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.validators.PasswordFormatException;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class CreateWorkerCommandHandler implements CommandHandler<Worker, CreateWorkerCommand>
{
    private final Repository<Worker> workerRepository;
    private final Repository<Contractor> contractorRepository;
    private final PasswordValidator passwordValidator;

    public CreateWorkerCommandHandler(
            Repository workerRepository,
            Repository contractorRepository,
            PasswordValidator passwordValidator)
    {
        this.workerRepository = workerRepository;
        this.contractorRepository = contractorRepository;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public Worker handle(CreateWorkerCommand command)
    {
        if (this.contractorRepository.read().anyMatch(contractor -> contractor.getLogin().equals(command.login)) ||
                this.workerRepository.read().anyMatch(worker -> worker.getLogin().equals(command.login)))
        {
            System.out.println("Error: login already in use");
            return null;
        }

        try {
            this.passwordValidator.validate(command.password);

            Worker worker = WorkerBuilder.init(Id.generate(), command.login, Date.now())
                    .setPassword(command.password)
                    .setName(command.name)
                    .setService(command.service)
                    .setDepartment(command.department)
                    .build();

            this.workerRepository.create(worker);

            return worker;

        } catch (PasswordFormatException | NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
