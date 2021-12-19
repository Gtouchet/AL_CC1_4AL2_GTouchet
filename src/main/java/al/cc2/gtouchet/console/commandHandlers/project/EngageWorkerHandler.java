package al.cc2.gtouchet.console.commandHandlers.project;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.project.dtos.EngageFireWorkerDto;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.Objects;

public class EngageWorkerHandler implements CommandHandler
{
    private final ServiceHandler<Project, EngageFireWorkerDto> serviceHandler;

    public EngageWorkerHandler(ServiceHandler<Project, EngageFireWorkerDto> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.ENGAGE_WORKER.parameters)
        {
            Project project = this.serviceHandler.handle(new EngageFireWorkerDto(
                    Id.fromString(params[1].toLowerCase()),
                    Id.fromString(params[2].toLowerCase())
            ));
            if (project != null)
            {
                System.out.println(project);
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.ENGAGE_WORKER);
        }
    }
}
