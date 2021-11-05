package esgi.al.cliInterpreter;

public enum Command
{
    CREATE,
    GET,
    UPDATE,
    DELETE,
    QUIT;

    public static Command getFromString(String value)
    {
        for (Command command : Command.values())
        {
            if (command.toString().equals(value))
            {
                return command;
            }
        }
        return null;
    }
}
