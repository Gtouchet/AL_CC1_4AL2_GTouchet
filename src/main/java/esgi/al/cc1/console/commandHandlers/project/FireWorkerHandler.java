package esgi.al.cc1.console.commandHandlers.project;

import esgi.al.cc1.application.ProjectService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.valueObjects.Id;

public class FireWorkerHandler implements CommandHandler
{
    private final ProjectService projectService;

    public FireWorkerHandler(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.fireWorker.parameters)
        {
            this.projectService.fireWorker(
                    Id.fromString(params[1].toLowerCase()),
                    Id.fromString(params[2].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.fireWorker);
        }
    }
}
