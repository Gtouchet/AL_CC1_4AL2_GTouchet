package al.cc2.gtouchet.console.handlers.project;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.project.ReadAllProjectQuery;
import al.cc2.gtouchet.application.services.dtos.project.ReadProjectQuery;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.Project;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.List;
import java.util.Objects;

public class ReadProjectConsoleHandler implements ConsoleHandler
{
    private final QueryHandler<List<Project>, ReadAllProjectQuery> queryHandlerAll;
    private final QueryHandler<Project, ReadProjectQuery> queryHandlerId;

    public ReadProjectConsoleHandler(
            QueryHandler queryHandlerAll,
            QueryHandler queryHandlerId) throws NullPointerException
    {
        this.queryHandlerAll = Objects.requireNonNull(queryHandlerAll);
        this.queryHandlerId = Objects.requireNonNull(queryHandlerId);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.READ_PROJECT.parameters)
        {
            List<Project> projects = this.queryHandlerAll.handle(null);
            if (projects.size() > 0)
            {
                projects.forEach(System.out::println);
            }
            else
            {
                System.out.println("No Project registered yet");
            }
        }
        else if (params.length == ConsoleCommand.READ_PROJECT.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            Project project = this.queryHandlerId.handle(new ReadProjectQuery(
                    Id.fromString(params[1].toLowerCase())
            ));
            if (project != null)
            {
                System.out.println(project);
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.READ_PROJECT);
        }
    }
}
