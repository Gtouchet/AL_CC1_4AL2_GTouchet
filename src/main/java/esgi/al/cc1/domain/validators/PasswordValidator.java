package esgi.al.cc1.domain.validators;

import esgi.al.cc1.domain.valueObjects.Password;

import java.util.regex.Pattern;

public class PasswordValidator
{
    public void validate(Password password) throws PasswordFormatException
    {
        boolean isValid = Pattern
                .compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!:;,?./§ù%*µ$£@(){}=+-])(?=\\S+$).{8,}")
                .matcher(password.toString())
                .matches();

        if (!isValid)
        {
            throw new PasswordFormatException();
        }
    }
}
