package esgi.al.cc1.application.commandHandlers.worker;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.controllers.Controller;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;

import java.util.List;
import java.util.stream.Collectors;

public class ReadWorkerHandler implements CommandHandler
{
    private final Controller<Worker> workerController;

    public ReadWorkerHandler(Controller<Worker> workerController)
    {
        this.workerController = workerController;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgument
    {
        if (params.length == Command.readWorker.parametersCount)
        {
            List<Worker> workers = this.workerController.read().collect(Collectors.toList());
            if (workers.size() == 0) {
                System.out.println("No worker registered yet");
            }
            else {
                workers.forEach(System.out::println);
            }
        }
        else if (params.length == Command.readWorker.parametersOverloadCount)
        {
            try {
                System.out.println(this.workerController.read(params[1]));
            } catch (ElementNotFound e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            throw new WrongNumberOfArgument(Command.readWorker);
        }
    }
}
