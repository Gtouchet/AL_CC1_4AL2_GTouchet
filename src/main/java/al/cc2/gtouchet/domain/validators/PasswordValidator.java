package al.cc2.gtouchet.domain.validators;

import al.cc2.gtouchet.domain.valueObjects.Password;

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
