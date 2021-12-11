package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.ProjectService;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class DeleteProjectHandler implements CommandHandler
{
    private final ProjectService projectService;

    public DeleteProjectHandler(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.DELETE_PROJECT.parameters)
        {
            this.projectService.delete(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.DELETE_PROJECT);
        }
    }
}
