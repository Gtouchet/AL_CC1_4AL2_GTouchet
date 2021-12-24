package al.cc2.gtouchet.console.handlers.project;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.project.UpdateProjectCommand;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.Objects;

public class UpdateProjectConsoleHandler implements ConsoleHandler
{
    private final CommandHandler<Project, UpdateProjectCommand> commandHandler;

    public UpdateProjectConsoleHandler(CommandHandler commandHandler) throws NullPointerException
    {
        this.commandHandler = Objects.requireNonNull(commandHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.UPDATE_PROJECT.parameters)
        {
            try {
                Project project = this.commandHandler.handle(new UpdateProjectCommand(
                        Id.fromString(params[1].toLowerCase()),
                        Id.fromString(params[2].toLowerCase()),
                        Integer.parseInt(params[3])
                ));
                if (project != null)
                {
                    System.out.println(project);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: could not parse [" + params[3] + "] as a department");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.UPDATE_PROJECT);
        }
    }
}
