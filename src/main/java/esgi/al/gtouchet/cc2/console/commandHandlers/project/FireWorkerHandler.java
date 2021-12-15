package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.application.projectServices.fireWorker.FireWorkerDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Project;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class FireWorkerHandler implements CommandHandler
{
    private final ServiceHandler<Project, FireWorkerDto> serviceHandler;

    public FireWorkerHandler(ServiceHandler<Project, FireWorkerDto> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.FIRE_WORKER.parameters)
        {
            Project project = this.serviceHandler.handle(new FireWorkerDto(
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
