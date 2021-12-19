package al.cc2.gtouchet.application.services.worker;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

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
