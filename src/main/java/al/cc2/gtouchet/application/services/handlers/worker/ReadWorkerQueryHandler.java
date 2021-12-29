package al.cc2.gtouchet.application.services.handlers.worker;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.worker.ReadWorkerQuery;
import al.cc2.gtouchet.domain.models.user.Worker;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class ReadWorkerQueryHandler implements QueryHandler<Worker, ReadWorkerQuery>
{
    private final Repository<Worker> workerRepository;

    public ReadWorkerQueryHandler(Repository workerRepository)
    {
        this.workerRepository = workerRepository;
    }

    @Override
    public Worker handle(ReadWorkerQuery query)
    {
        try {
            return this.workerRepository.read(query.id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
