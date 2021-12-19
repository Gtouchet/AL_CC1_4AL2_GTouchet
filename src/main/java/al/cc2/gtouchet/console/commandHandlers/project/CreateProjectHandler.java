package al.cc2.gtouchet.console.commandHandlers.project;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.project.dtos.CreateProjectDto;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.Objects;

public class CreateProjectHandler implements CommandHandler
{
    private final ServiceHandler<Project, CreateProjectDto> serviceHandler;

    public CreateProjectHandler(ServiceHandler<Project, CreateProjectDto> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
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
