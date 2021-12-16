package esgi.al.gtouchet.cc2.application.services.worker;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.worker.dtos.CreateWorkerDto;
import esgi.al.gtouchet.cc2.domain.builders.WorkerBuilder;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordFormatException;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Date;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

public class CreateWorkerServiceHandler implements ServiceHandler<Worker, CreateWorkerDto>
{
    private final Repository<Worker> workerRepository;
    private final Repository<Contractor> contractorRepository;
    private final PasswordValidator passwordValidator;

    public CreateWorkerServiceHandler(
            Repository<Worker> workerRepository,
            Repository<Contractor> contractorRepository,
            PasswordValidator passwordValidator)
    {
        this.workerRepository = workerRepository;
        this.contractorRepository = contractorRepository;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public Worker handle(CreateWorkerDto command)
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
