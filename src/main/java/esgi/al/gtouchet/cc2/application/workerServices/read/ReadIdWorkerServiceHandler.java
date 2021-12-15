package esgi.al.gtouchet.cc2.application.workerServices.read;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
