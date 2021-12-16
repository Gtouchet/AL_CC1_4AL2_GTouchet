package esgi.al.gtouchet.cc2.application.services.worker.read;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ReadAllWorkerServiceHandler implements ServiceHandler<List<Worker>, Void>
{
    private final Repository<Worker> workerRepository;

    public ReadAllWorkerServiceHandler(Repository<Worker> workerRepository)
    {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> handle(Void command)
    {
        return this.workerRepository.read().collect(Collectors.toList());
    }
}
