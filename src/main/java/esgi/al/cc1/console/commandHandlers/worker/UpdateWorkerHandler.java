package esgi.al.cc1.console.commandHandlers.worker;

import esgi.al.cc1.application.WorkerService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.models.Service;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

import java.util.IllegalFormatException;

public class UpdateWorkerHandler implements CommandHandler
{
    private final WorkerService workerService;

    public UpdateWorkerHandler(WorkerService workerService)
    {
        this.workerService = workerService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.updateWorker.parameters)
        {
            try {
                this.workerService.update(
                        Id.fromString(params[1].toLowerCase()),
                        Password.of(params[2]),
                        params[3],
                        Service.valueOf(params[4].toLowerCase()),
                        Integer.parseInt(params[5])
                );
            } catch (IllegalFormatException e) {
                System.out.println("Error: could not parse [" + params[5] + "] as a department");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: unknown service [" + params[4] + "]");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.updateWorker);
        }
    }
}
