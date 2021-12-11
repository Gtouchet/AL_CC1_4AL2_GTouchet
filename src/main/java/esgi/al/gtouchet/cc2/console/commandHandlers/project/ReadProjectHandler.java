package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.ProjectService;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

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
        if (params.length == Command.READ_PROJECT.parameters)
        {
            this.projectService.read();
        }
        else if (params.length == Command.READ_PROJECT.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            this.projectService.read(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.READ_PROJECT);
        }
    }
}
