package esgi.al.cc1.console.commandHandlers.project;

import esgi.al.cc1.application.ProjectService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.valueObjects.Id;

public class UpdateProjectHandler implements CommandHandler
{
    private final ProjectService projectService;

    public UpdateProjectHandler(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.updateProject.parameters)
        {
            try {
                this.projectService.update(
                        Id.fromString(params[1].toLowerCase()),
                        Id.fromString(params[2].toLowerCase()),
                        Integer.parseInt(params[3])
                );
            } catch (NumberFormatException e) {
                System.out.println("Error: could not parse [" + params[3] + "] as a department");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.updateProject);
        }
    }
}
