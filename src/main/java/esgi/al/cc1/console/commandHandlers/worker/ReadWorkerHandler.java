package esgi.al.cc1.console.commandHandlers.worker;

import esgi.al.cc1.application.WorkerService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.valueObjects.Id;

public class ReadWorkerHandler implements CommandHandler
{
    private final WorkerService workerService;

    public ReadWorkerHandler(WorkerService workerService)
    {
        this.workerService = workerService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.readWorker.parameters)
        {
            this.workerService.read();
        }
        else if (params.length == Command.readWorker.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            this.workerService.read(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.readWorker);
        }
    }
}
