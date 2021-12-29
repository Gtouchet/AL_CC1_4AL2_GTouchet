package al.cc2.gtouchet.console.handlers.project;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.project.EngageFireWorkerCommand;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.Objects;

public final class FireWorkerConsoleHandler implements ConsoleHandler
{
    private final CommandHandler<Project, EngageFireWorkerCommand> commandHandler;

    public FireWorkerConsoleHandler(CommandHandler commandHandler) throws NullPointerException
    {
        this.commandHandler = Objects.requireNonNull(commandHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.FIRE_WORKER.parameters)
        {
            Project project = this.commandHandler.handle(new EngageFireWorkerCommand(
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
            throw new WrongNumberOfArgumentException(ConsoleCommand.FIRE_WORKER);
        }
    }
}
