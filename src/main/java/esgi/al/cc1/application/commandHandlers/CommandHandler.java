package esgi.al.cc1.application.commandHandlers;

import esgi.al.cc1.exceptions.WrongNumberOfArgument;

public interface CommandHandler
{
    void handle(String[] params) throws WrongNumberOfArgument;
}
