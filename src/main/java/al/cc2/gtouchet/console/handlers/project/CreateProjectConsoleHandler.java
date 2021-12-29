package al.cc2.gtouchet.console.handlers.project;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.project.CreateProjectCommand;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.project.Project;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

import java.util.Objects;

public final class CreateProjectConsoleHandler implements ConsoleHandler
{
    private final CommandHandler<Project, CreateProjectCommand> commandHandler;

    public CreateProjectConsoleHandler(CommandHandler commandHandler) throws NullPointerException
    {
        this.commandHandler = Objects.requireNonNull(commandHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.CREATE_PROJECT.parameters)
        {
            try {
                Project project = this.commandHandler.handle(new CreateProjectCommand(
                        EntityId.fromString(params[1].toLowerCase()),
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
            throw new WrongNumberOfArgumentException(ConsoleCommand.CREATE_PROJECT);
        }
    }
}
