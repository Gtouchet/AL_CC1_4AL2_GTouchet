package al.cc2.gtouchet.domain.validators;

public class PasswordFormatException extends RuntimeException
{
    public PasswordFormatException()
    {
        super("Error: wrong password format, needs at least 8 characters, a lower and upper case, a digit, and a special character");
    }
}