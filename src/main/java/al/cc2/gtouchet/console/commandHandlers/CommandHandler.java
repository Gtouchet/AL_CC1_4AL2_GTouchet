package al.cc2.gtouchet.console.commandHandlers;

import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;

public interface CommandHandler
{
    void handle(String[] params) throws WrongNumberOfArgumentException;
}
