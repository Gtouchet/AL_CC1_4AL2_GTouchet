package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.project.engageWorker.EngageWorkerDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class EngageWorkerHandler implements CommandHandler
{
    private final ServiceHandler<Project, EngageWorkerDto> serviceHandler;

    public EngageWorkerHandler(ServiceHandler<Project, EngageWorkerDto> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.ENGAGE_WORKER.parameters)
        {
            Project project = this.serviceHandler.handle(new EngageWorkerDto(
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
