package esgi.al.cc1.console.commandHandlers;

import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;

public interface CommandHandler
{
    void handle(String[] params) throws WrongNumberOfArgumentException;
}
