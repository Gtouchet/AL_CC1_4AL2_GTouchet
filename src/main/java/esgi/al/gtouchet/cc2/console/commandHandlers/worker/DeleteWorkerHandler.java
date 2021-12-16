package esgi.al.gtouchet.cc2.console.commandHandlers.worker;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class DeleteWorkerHandler implements CommandHandler
{
    private final ServiceHandler<Boolean, Id> serviceHandler;

    public DeleteWorkerHandler(ServiceHandler<Boolean, Id> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.DELETE_WORKER.parameters)
        {
            boolean success = this.serviceHandler.handle(
                    Id.fromString(params[1].toLowerCase())
            );
            if (success)
            {
                System.out.println("Worker ID " + params[1].toLowerCase() + " deleted");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.DELETE_WORKER);
        }
    }
}
