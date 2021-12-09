package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.ProjectService;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class CreateProjectHandler implements CommandHandler
{
    private final ProjectService projectService;

    public CreateProjectHandler(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.createProject.parameters)
        {
            try {
                this.projectService.create(
                        Id.fromString(params[1].toLowerCase()),
                        Integer.parseInt(params[2])
                );
            } catch (NumberFormatException e) {
                System.out.println("Error: could not parse [" + params[2] + "] as a department");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.createProject);
        }
    }
}
