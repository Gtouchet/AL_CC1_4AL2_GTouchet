package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.dtos.UpdateProjectDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class UpdateProjectHandler implements CommandHandler
{
    private final ServiceHandler<Project, UpdateProjectDto> serviceHandler;

    public UpdateProjectHandler(ServiceHandler<Project, UpdateProjectDto> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.UPDATE_PROJECT.parameters)
        {
            try {
                Project project = this.serviceHandler.handle(new UpdateProjectDto(
                        Id.fromString(params[1].toLowerCase()),
                        Id.fromString(params[2].toLowerCase()),
                        Integer.parseInt(params[3])
                ));
                if (project != null)
                {
                    System.out.println(project);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: could not parse [" + params[3] + "] as a department");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.UPDATE_PROJECT);
        }
    }
}
