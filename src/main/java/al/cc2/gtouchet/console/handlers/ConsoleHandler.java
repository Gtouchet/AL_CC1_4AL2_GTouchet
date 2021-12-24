package al.cc2.gtouchet.console.handlers;

import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;

public interface ConsoleHandler
{
    void handle(String[] params) throws WrongNumberOfArgumentException;
}
