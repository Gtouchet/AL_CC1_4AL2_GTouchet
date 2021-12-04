package esgi.al.cc1.domain.valueObjects;

public class PasswordFormatException extends Exception
{
    public PasswordFormatException()
    {
        super("Error: wrong password format, needs at least 8 characters, a lower and upper case, a digit, and a special character");
    }
}