package al.cc2.gtouchet.console.handlers.worker;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.worker.ReadAllWorkerQuery;
import al.cc2.gtouchet.application.services.dtos.worker.ReadWorkerQuery;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.List;
import java.util.Objects;

public class ReadWorkerConsoleHandler implements ConsoleHandler
{
    private final QueryHandler<List<Worker>, ReadAllWorkerQuery> queryHandlerAll;
    private final QueryHandler<Worker, ReadWorkerQuery> queryHandlerId;

    public ReadWorkerConsoleHandler(
            QueryHandler queryHandlerAll,
            QueryHandler queryHandlerId) throws NullPointerException
    {
        this.queryHandlerAll = Objects.requireNonNull(queryHandlerAll);
        this.queryHandlerId = Objects.requireNonNull(queryHandlerId);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.READ_WORKER.parameters)
        {
            List<Worker> workers = this.queryHandlerAll.handle(null);
            if (workers.size() > 0)
            {
                workers.forEach(System.out::println);
            }
            else
            {
                System.out.println("No Worker registered yet");
            }
        }
        else if (params.length == ConsoleCommand.READ_WORKER.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            Worker worker = this.queryHandlerId.handle(new ReadWorkerQuery(
                    Id.fromString(params[1].toLowerCase())
            ));
            if (worker != null)
            {
                System.out.println(worker);
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.READ_WORKER);
        }
    }
}
