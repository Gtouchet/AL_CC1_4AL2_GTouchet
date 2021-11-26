package esgi.al.cc1.application.exceptions;

import esgi.al.cc1.application.enumerators.Command;

public class WrongNumberOfArgument extends Exception
{
    public WrongNumberOfArgument(Command command)
    {
        super("Error: wrong number of argument for command [" + command.keyword + "]\n" + command.usage);
    }
}
