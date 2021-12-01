package esgi.al.cc1.console.engine;

public class WrongNumberOfArgumentException extends Exception
{
    public WrongNumberOfArgumentException(Command command)
    {
        super("Error: wrong number of argument for command [" + command.keyword + "]\n" + command.usage);
    }
}
