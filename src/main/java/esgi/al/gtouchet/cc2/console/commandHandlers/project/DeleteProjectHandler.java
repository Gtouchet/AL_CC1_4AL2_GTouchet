package esgi.al.gtouchet.cc2.console.commandHandlers.project;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

import java.util.Objects;

public class DeleteProjectHandler implements CommandHandler
{
    private final ServiceHandler<Boolean, Id> serviceHandler;

    public DeleteProjectHandler(ServiceHandler<Boolean, Id> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.DELETE_PROJECT.parameters)
        {
            boolean success = this.serviceHandler.handle(
                    Id.fromString(params[1].toLowerCase())
            );
            if (success)
            {
                System.out.println("Project ID " + params[1].toLowerCase() + " deleted");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.DELETE_PROJECT);
        }
    }
}
