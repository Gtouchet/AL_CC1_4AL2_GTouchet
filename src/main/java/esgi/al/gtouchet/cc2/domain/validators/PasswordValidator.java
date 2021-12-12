package esgi.al.gtouchet.cc2.domain.validators;

import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

public class PasswordValidator
{
    public void validate(Password password) throws PasswordFormatException
    {
        if (!password.toString().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!:;,?./§ù%*µ$£@(){}=+-])(?=\\S+$).{8,}"))
        {
            throw new PasswordFormatException();
        }
    }
}
