package esgi.al.gtouchet.cc2.application.services.worker.update;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.builders.WorkerBuilder;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordFormatException;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
