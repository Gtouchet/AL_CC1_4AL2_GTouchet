package al.cc2.gtouchet.console.commandHandlers.worker;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.List;
import java.util.Objects;

public class ReadWorkerHandler implements CommandHandler
{
    private final ServiceHandler<List<Worker>, Void> serviceHandlerAll;
    private final ServiceHandler<Worker, Id> serviceHandlerId;

    public ReadWorkerHandler(
            ServiceHandler<List<Worker>, Void> serviceHandlerAll,
            ServiceHandler<Worker, Id> serviceHandlerId) throws NullPointerException
    {
        this.serviceHandlerAll = Objects.requireNonNull(serviceHandlerAll);
        this.serviceHandlerId = Objects.requireNonNull(serviceHandlerId);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.READ_WORKER.parameters)
        {
            List<Worker> workers = this.serviceHandlerAll.handle(null);
            if (workers.size() > 0)
            {
                workers.forEach(System.out::println);
            }
            else
            {
                System.out.println("No Worker registered yet");
            }
        }
        else if (params.length == Command.READ_WORKER.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            Worker worker = this.serviceHandlerId.handle(Id.fromString(params[1].toLowerCase()));
            if (worker != null)
            {
                System.out.println(worker);
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.READ_WORKER);
        }
    }
}
