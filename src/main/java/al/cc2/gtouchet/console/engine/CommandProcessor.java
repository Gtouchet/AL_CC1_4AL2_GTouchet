package al.cc2.gtouchet.console.engine;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.console.handlers.miscellaneous.HelpConsoleHandler;

import java.lang.reflect.InvocationTargetException;

public class CommandProcessor
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
            ConsoleHandler consoleHandler;

            if (consoleCommand.serviceHandlers.length == 1)
            {
                consoleHandler = (ConsoleHandler) Class
                        .forName(consoleCommand.consoleHandler.getName())
                        .getConstructor(new Class[] { CommandHandler.class })
                        .newInstance(this.handlersContainer.getCommandHandler(consoleCommand.serviceHandlers[0]));
            }
            else
            {
                consoleHandler = (ConsoleHandler) Class
                        .forName(consoleCommand.consoleHandler.getName())
                        .getConstructor(new Class[] { QueryHandler.class, QueryHandler.class })
                        .newInstance(
                                this.handlersContainer.getQueryHandler(consoleCommand.serviceHandlers[0]),
                                this.handlersContainer.getQueryHandler(consoleCommand.serviceHandlers[1])
                        );
            }

            consoleHandler.handle(params);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                InvocationTargetException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        } catch (WrongNumberOfArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
