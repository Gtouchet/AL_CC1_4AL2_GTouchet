package esgi.al.exceptions.modelsExceptions;

public class InvalidAddressParameter extends Throwable
{
    public InvalidAddressParameter(String parameter, String value)
    {
        super("Invalid address parameter for " + parameter + " [" + value + "]");
    }
}
