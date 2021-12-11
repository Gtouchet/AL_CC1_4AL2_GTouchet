package esgi.al.gtouchet.cc2.console.commandHandlers.miscellaneous;

import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;

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
