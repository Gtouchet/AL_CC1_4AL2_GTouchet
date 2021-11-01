package esgi.al.exceptions.modelsExceptions;

public class InvalidUserParameter extends Throwable
{
    public InvalidUserParameter(String parameter, String value)
    {
        super("Invalid user parameter for " + parameter + " [" + value + "]");
    }
}
