package esgi.al;

import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
import esgi.al.validators.UserValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PasswordValidatorTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void passwordValidator_lengthTooShort() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);
        UserValidator.validatePassword("12Az,;");
    }

    @Test
    public void passwordValidator_noUpperCase() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);
        UserValidator.validatePassword("12azer,;");
    }

    @Test
    public void passwordValidator_noLowerCase() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);
        UserValidator.validatePassword("12AZER,;");
    }

    @Test
    public void passwordValidator_noSpecialCharacter() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);
        UserValidator.validatePassword("1234AZer");
    }

    @Test
    public void passwordValidator_isValid() throws InvalidUserParameter
    {
        try {
            UserValidator.validatePassword("12AZer,;");
            assertTrue(true);
        } catch (InvalidUserParameter e) {
            fail();
        }
    }
}
