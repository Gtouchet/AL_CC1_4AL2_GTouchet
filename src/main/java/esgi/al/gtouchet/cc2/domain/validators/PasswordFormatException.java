package esgi.al.gtouchet.cc2.domain.validators;

public class PasswordFormatException extends Exception
{
    public PasswordFormatException()
    {
        super("Error: wrong password format, needs at least 8 characters, a lower and upper case, a digit, and a special character");
    }
}