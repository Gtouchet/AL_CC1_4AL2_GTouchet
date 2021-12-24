package al.cc2.gtouchet.application.services.handlers.worker;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.worker.ReadAllWorkerQuery;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ReadAllWorkerQueryHandler implements QueryHandler<List<Worker>, ReadAllWorkerQuery>
{
    private final Repository<Worker> workerRepository;

    public ReadAllWorkerQueryHandler(Repository<Worker> workerRepository)
    {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> handle(ReadAllWorkerQuery query)
    {
        return this.workerRepository.read().collect(Collectors.toList());
    }
}
