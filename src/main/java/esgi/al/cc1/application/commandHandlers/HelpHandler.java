package esgi.al.cc1.application.commandHandlers;

import esgi.al.cc1.application.enumerators.Command;

public class HelpHandler implements CommandHandler
{
    @Override
    public void handle(String[] params)
    {
        if (params.length == 1)
        {
            System.out.println("---Available commands---");
            Command.getCommands().forEach(command -> {
                if (command.usage != null) System.out.println(command.usage);
            });
            System.out.println("---Command input---");
        }
        else
        {
            Command command = Command.getCommand(params[1]);
            System.out.println(command != Command.none ?
                    "Usage: " + command.usage :
                    "Unrecognized command [" + params[1].toUpperCase() + "], type HELP to see the commands list");
        }
    }
}
