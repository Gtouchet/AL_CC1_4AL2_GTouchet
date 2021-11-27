package esgi.al.cc1.application.commandHandlers;

import esgi.al.cc1.application.enumerators.Command;

public class HelpHandler implements CommandHandler
{
    @Override
    public void handle(String[] params)
    {
        try {
            System.out.println("Usage: " + Command.getCommand(params[1]).usage);
        } catch (IllegalArgumentException e) {
            System.out.println("Unrecognized command [" + params[1].toUpperCase() + "]");
        }
    }
}
