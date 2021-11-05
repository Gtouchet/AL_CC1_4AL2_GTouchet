package esgi.al.exceptions.modelsExceptions;

public class InvalidModelParameter extends Throwable
{
    public InvalidModelParameter(String parameter, String value)
    {
        super("Invalid parameter for " + parameter + " [" + value + "]");
    }
}
