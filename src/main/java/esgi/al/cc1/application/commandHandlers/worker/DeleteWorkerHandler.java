package esgi.al.cc1.application.commandHandlers.worker;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.controllers.Controller;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;

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
        if (params.length == Command.deleteWorker.parametersCount)
        {
            try {
                this.workerController.remove(params[1]);
            } catch (ElementNotFound e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            throw new WrongNumberOfArgument(Command.deleteWorker);
        }
    }
}
