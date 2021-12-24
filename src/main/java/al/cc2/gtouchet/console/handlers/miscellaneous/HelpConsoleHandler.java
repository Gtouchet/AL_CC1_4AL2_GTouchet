package al.cc2.gtouchet.console.handlers.miscellaneous;

import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;

public class HelpConsoleHandler implements ConsoleHandler
{
    @Override
    public void handle(String[] params)
    {
        if (params.length == ConsoleCommand.HELP.parameters)
        {
            ConsoleCommand.getCommands().forEach(command -> {
                if (command.usage != null) System.out.println(command.usage);
            });
        }
        else
        {
            ConsoleCommand command = ConsoleCommand.getCommand(params[1]);
            System.out.println(command != ConsoleCommand.NONE ?
                    "Usage: " + command.usage :
                    "Unrecognized command [" + params[1].toUpperCase() + "], type HELP to see the commands list");
        }
    }
}
