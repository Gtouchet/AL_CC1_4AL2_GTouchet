package esgi.al.cc1.domain.valueObjects;

public class IllegalPasswordFormat extends Exception
{
    public IllegalPasswordFormat()
    {
        super("Error: wrong password format, needs at least 8 characters, a lower and upper case, a digit, and a special character");
    }
}

/*  // todo: find where to put it...
    private void validate(String password) throws IllegalPasswordFormat
    {
        if (!password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!:;,?./§ù%*µ$£@(){}=+-])(?=\\S+$).{8,}"))
        {
            throw new IllegalPasswordFormat();
        }
    }
*/