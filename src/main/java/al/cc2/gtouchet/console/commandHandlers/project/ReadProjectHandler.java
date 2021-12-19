package al.cc2.gtouchet.console.commandHandlers.project;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.List;
import java.util.Objects;

public class ReadProjectHandler implements CommandHandler
{
    private final ServiceHandler<List<Project>, Void> serviceHandlerAll;
    private final ServiceHandler<Project, Id> serviceHandlerId;

    public ReadProjectHandler(
            ServiceHandler<List<Project>, Void> serviceHandlerAll,
            ServiceHandler<Project, Id> serviceHandlerId) throws NullPointerException
    {
        this.serviceHandlerAll = Objects.requireNonNull(serviceHandlerAll);
        this.serviceHandlerId = Objects.requireNonNull(serviceHandlerId);
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
