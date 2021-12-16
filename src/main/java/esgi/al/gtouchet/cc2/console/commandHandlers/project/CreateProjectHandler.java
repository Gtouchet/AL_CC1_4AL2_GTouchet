package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.create.CreateProjectDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class CreateProjectHandler implements CommandHandler
{
    private final ServiceHandler<Project, CreateProjectDto> serviceHandler;

    public CreateProjectHandler(ServiceHandler<Project, CreateProjectDto> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.CREATE_PROJECT.parameters)
        {
            try {
                Project project = this.serviceHandler.handle(new CreateProjectDto(
                        Id.fromString(params[1].toLowerCase()),
                        Integer.parseInt(params[2])
                ));
                if (project != null)
                {
                    System.out.println(project);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: could not parse [" + params[2] + "] as a department");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.CREATE_PROJECT);
        }
    }
}
