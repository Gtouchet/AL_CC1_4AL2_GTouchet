package al.cc2.gtouchet.console.commandHandlers.miscellaneous;

import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;

public class HelpHandler implements CommandHandler
{
    @Override
    public void handle(String[] params)
    {
        if (params.length == Command.HELP.parameters)
        {
            Command.getCommands().forEach(command -> {
                if (command.usage != null) System.out.println(command.usage);
            });
        }
        else
        {
            Command command = Command.getCommand(params[1]);
            System.out.println(command != Command.NONE ?
                    "Usage: " + command.usage :
                    "Unrecognized command [" + params[1].toUpperCase() + "], type HELP to see the commands list");
        }
    }
}
