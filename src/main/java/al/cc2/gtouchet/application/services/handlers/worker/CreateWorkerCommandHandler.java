package al.cc2.gtouchet.application.services.handlers.worker;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.worker.CreateWorkerCommand;
import al.cc2.gtouchet.domain.builders.WorkerBuilder;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.domain.validators.PasswordFormatException;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class CreateWorkerCommandHandler implements CommandHandler<Worker, CreateWorkerCommand>
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
        if (this.contractorRepository.read().anyMatch(contractor -> contractor.getCredentials().getLogin().equals(command.login)) ||
                this.workerRepository.read().anyMatch(worker -> worker.getCredentials().getLogin().equals(command.login)))
        {
            System.out.println("Error: login already in use");
            return null;
        }

        try {
            this.passwordValidator.validate(command.password);

            Worker worker = WorkerBuilder.init(EntityId.generate(), command.login, Clock.now())
                    .setPassword(command.password)
                    .setName(command.name)
                    .setService(command.workerService)
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
