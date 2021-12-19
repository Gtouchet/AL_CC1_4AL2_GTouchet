package al.cc2.gtouchet.application.services.worker;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class ReadIdWorkerServiceHandler implements ServiceHandler<Worker, Id>
{
    private final Repository<Worker> workerRepository;

    public ReadIdWorkerServiceHandler(Repository<Worker> workerRepository)
    {
        this.workerRepository = workerRepository;
    }

    @Override
    public Worker handle(Id command)
    {
        try {
            return this.workerRepository.read(command);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
