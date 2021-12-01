package esgi.al.cc1.console.commandHandlers.project;

import esgi.al.cc1.application.ProjectService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.valueObjects.Id;

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
        if (params.length == Command.deleteProject.parameters)
        {
            this.projectService.delete(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.deleteProject);
        }
    }
}
