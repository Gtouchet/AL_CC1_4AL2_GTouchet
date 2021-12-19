package al.cc2.gtouchet.application.services.worker;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.worker.dtos.UpdateWorkerDto;
import al.cc2.gtouchet.domain.builders.WorkerBuilder;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.validators.PasswordFormatException;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class UpdateWorkerServiceHandler implements ServiceHandler<Worker, UpdateWorkerDto>
{
    private final Repository<Worker> workerRepository;
    private final PasswordValidator passwordValidator;

    public UpdateWorkerServiceHandler(
            Repository<Worker> workerRepository,
            PasswordValidator passwordValidator)
    {
        this.workerRepository = workerRepository;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public Worker handle(UpdateWorkerDto command)
    {
        try {
            Worker worker = this.workerRepository.read(command.id);

            this.passwordValidator.validate(command.password);

            worker = WorkerBuilder.init(worker)
                    .setPassword(command.password)
                    .setName(command.name)
                    .setService(command.service)
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
