package esgi.al.gtouchet.cc2.console.commandHandlers.worker;

import esgi.al.gtouchet.cc2.application.WorkerService;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

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
        if (params.length == Command.READ_WORKER.parameters)
        {
            this.workerService.read();
        }
        else if (params.length == Command.READ_WORKER.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            this.workerService.read(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.READ_WORKER);
        }
    }
}
