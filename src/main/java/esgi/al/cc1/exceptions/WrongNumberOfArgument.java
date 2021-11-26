package esgi.al.cc1.exceptions;

import esgi.al.cc1.application.console.Command;

public class WrongNumberOfArgument extends Exception
{
    public WrongNumberOfArgument(Command command)
    {
        super("Error: wrong number of argument for command [" + command.keyword + "]\n" + command.usage);
    }
}
