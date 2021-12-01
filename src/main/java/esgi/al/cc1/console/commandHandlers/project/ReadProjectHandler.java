package esgi.al.cc1.console.commandHandlers.project;

import esgi.al.cc1.application.ProjectService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.valueObjects.Id;

public class ReadProjectHandler implements CommandHandler
{
    private final ProjectService projectService;

    public ReadProjectHandler(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.readProject.parameters)
        {
            this.projectService.read();
        }
        else if (params.length == Command.readProject.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            this.projectService.read(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.readProject);
        }
    }
}
