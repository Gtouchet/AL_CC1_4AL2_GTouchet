package esgi.al.gtouchet.cc2.console.commandHandlers.worker;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

import java.util.List;

public class ReadWorkerHandler implements CommandHandler
{
    private final ServiceHandler<List<Worker>, Void> serviceHandlerAll;
    private final ServiceHandler<Worker, Id> serviceHandlerId;

    public ReadWorkerHandler(
            ServiceHandler<List<Worker>, Void> serviceHandlerAll,
            ServiceHandler<Worker, Id> serviceHandlerId)
    {
        this.serviceHandlerAll = serviceHandlerAll;
        this.serviceHandlerId = serviceHandlerId;
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
