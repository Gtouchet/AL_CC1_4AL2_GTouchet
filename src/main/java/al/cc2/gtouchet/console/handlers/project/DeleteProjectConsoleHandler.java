package al.cc2.gtouchet.console.handlers.project;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.project.DeleteProjectCommand;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.Objects;

public final class DeleteProjectConsoleHandler implements ConsoleHandler
{
    private final CommandHandler<Boolean, DeleteProjectCommand> commandHandler;

    public DeleteProjectConsoleHandler(CommandHandler commandHandler) throws NullPointerException
    {
        this.commandHandler = Objects.requireNonNull(commandHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.DELETE_PROJECT.parameters)
        {
            boolean success = this.commandHandler.handle(new DeleteProjectCommand(
                    Id.fromString(params[1].toLowerCase())
            ));
            if (success)
            {
                System.out.println("Project ID " + params[1].toLowerCase() + " deleted");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.DELETE_PROJECT);
        }
    }
}
