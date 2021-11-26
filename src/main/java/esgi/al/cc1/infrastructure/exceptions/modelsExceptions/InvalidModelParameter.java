package esgi.al.cc1.infrastructure.exceptions.modelsExceptions;

public class InvalidModelParameter extends Exception
{
    public InvalidModelParameter(String parameter, String value)
    {
        super("Invalid parameter for " + parameter + " [" + value + "]");
    }
}
