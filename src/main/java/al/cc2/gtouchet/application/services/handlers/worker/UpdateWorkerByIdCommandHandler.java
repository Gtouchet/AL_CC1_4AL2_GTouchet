package al.cc2.gtouchet.application.services.handlers.worker;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.worker.UpdateWorkerCommand;
import al.cc2.gtouchet.domain.builders.WorkerBuilder;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.domain.validators.PasswordFormatException;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class UpdateWorkerByIdCommandHandler implements CommandHandler<Worker, UpdateWorkerCommand>
{
    private final Repository<Worker> workerRepository;
    private final PasswordValidator passwordValidator;

    public UpdateWorkerByIdCommandHandler(
            Repository workerRepository,
            PasswordValidator passwordValidator)
    {
        this.workerRepository = workerRepository;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public Worker handle(UpdateWorkerCommand command)
    {
        try {
            Worker worker = this.workerRepository.read(command.id);

            this.passwordValidator.validate(command.password);

            worker = WorkerBuilder.init(worker)
                    .setPassword(command.password)
                    .setName(command.name)
                    .setService(command.workerService)
                    .setDepartment(command.department)
                    .build();

            this.workerRepository.update(command.id, worker);

            return worker;

        } catch (EntityNotFoundException | PasswordFormatException | NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
