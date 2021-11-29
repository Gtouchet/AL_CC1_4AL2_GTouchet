package esgi.al.cc1.application.commandHandlers.worker;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.controllers.Controller;

public class DeleteWorkerHandler implements CommandHandler
{
    private final Controller<Worker> workerController;

    public DeleteWorkerHandler(Controller<Worker> workerController)
    {
        this.workerController = workerController;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgument
    {
        if (params.length == Command.deleteWorker.parameters)
        {
            this.workerController.remove(params[1]);
        }
        else
        {
            throw new WrongNumberOfArgument(Command.deleteWorker);
        }
    }
}
