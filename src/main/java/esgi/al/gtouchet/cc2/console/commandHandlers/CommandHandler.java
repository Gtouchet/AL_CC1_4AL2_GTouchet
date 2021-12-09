package esgi.al.gtouchet.cc2.console.commandHandlers;

import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;

public interface CommandHandler
{
    void handle(String[] params) throws WrongNumberOfArgumentException;
}
