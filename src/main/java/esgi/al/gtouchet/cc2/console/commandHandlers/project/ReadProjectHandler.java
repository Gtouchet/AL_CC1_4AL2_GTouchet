package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

import java.util.List;

public class ReadProjectHandler implements CommandHandler
{
    private final ServiceHandler<List<Project>, Void> serviceHandlerAll;
    private final ServiceHandler<Project, Id> serviceHandlerId;

    public ReadProjectHandler(
            ServiceHandler<List<Project>, Void> serviceHandlerAll,
            ServiceHandler<Project, Id> serviceHandlerId)
    {
        this.serviceHandlerAll = serviceHandlerAll;
        this.serviceHandlerId = serviceHandlerId;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.READ_PROJECT.parameters)
        {
            List<Project> projects = this.serviceHandlerAll.handle(null);
            if (projects.size() > 0)
            {
                projects.forEach(System.out::println);
            }
            else
            {
                System.out.println("No Project registered yet");
            }
        }
        else if (params.length == Command.READ_PROJECT.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            Project project = this.serviceHandlerId.handle(Id.fromString(params[1].toLowerCase()));
            if (project != null)
            {
                System.out.println(project);
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.READ_PROJECT);
        }
    }
}
