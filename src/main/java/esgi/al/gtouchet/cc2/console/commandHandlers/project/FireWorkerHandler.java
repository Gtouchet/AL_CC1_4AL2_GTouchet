package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.dtos.EngageFireWorkerDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

import java.util.Objects;

public class FireWorkerHandler implements CommandHandler
{
    private final ServiceHandler<Project, EngageFireWorkerDto> serviceHandler;

    public FireWorkerHandler(ServiceHandler<Project, EngageFireWorkerDto> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.FIRE_WORKER.parameters)
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
            throw new WrongNumberOfArgumentException(Command.FIRE_WORKER);
        }
    }
}
