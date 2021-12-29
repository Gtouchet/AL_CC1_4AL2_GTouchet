package al.cc2.gtouchet.console.engine;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.console.handlers.miscellaneous.HelpConsoleHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CommandProcessor
{
    private final HandlersContainer handlersContainer;

    public CommandProcessor(HandlersContainer handlersContainer)
    {
        this.handlersContainer = handlersContainer;
    }

    public void process(String command)
    {
        String[] params = command.trim().split(" ");
        String commandKeyword = params[0].toUpperCase();

        if (commandKeyword.equals("") || commandKeyword.equals(ConsoleCommand.QUIT.keyword))
        {
            return;
        }

        ConsoleCommand consoleCommand = ConsoleCommand.getCommand(commandKeyword);

        if (consoleCommand == ConsoleCommand.NONE)
        {
            System.out.println("Unrecognized command [" + commandKeyword + "]");
            return;
        }

        if (consoleCommand == ConsoleCommand.HELP)
        {
            new HelpConsoleHandler().handle(params);
            return;
        }

        try {
            List<Class> constructorParametersTypes = new ArrayList<>();
            List<QueryHandler> instanceQueries = new ArrayList<>();
            List<CommandHandler> instanceCommands = new ArrayList<>();

            Arrays.asList(consoleCommand.serviceHandlers).forEach(handler -> {
                constructorParametersTypes.add(handler.getInterfaces()[0]);
                if (handler.getName().contains("Query")) { // TODO: refacto ?
                    instanceQueries.add(this.handlersContainer.getQueryHandler(handler));
                } else {
                    instanceCommands.add(this.handlersContainer.getCommandHandler(handler));
                }
            });

            ConsoleHandler consoleHandler = (ConsoleHandler) Class
                    .forName(consoleCommand.consoleHandler.getName())
                    .getConstructor(constructorParametersTypes.toArray(new Class[0]))
                    .newInstance(instanceQueries.size() > 0 ? instanceQueries.toArray() : instanceCommands.toArray());

            consoleHandler.handle(params);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                InvocationTargetException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        } catch (WrongNumberOfArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
