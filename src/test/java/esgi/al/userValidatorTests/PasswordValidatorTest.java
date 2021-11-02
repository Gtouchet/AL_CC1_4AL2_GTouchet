package esgi.al.userValidatorTests;

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
    public void passwordValidator_passwordNull() throws InvalidUserParameter
    {
        exception.expect(NullPointerException.class);

        UserValidator.validatePassword(null);
    }

    @Test
    public void passwordValidator_lengthTooShort() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);

        String password = "12Az,;";
        exception.expectMessage("Invalid user parameter for password [" + password + "]");
        UserValidator.validatePassword(password);
    }

    @Test
    public void passwordValidator_noUpperCase() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);

        String password = "12azer,;";
        exception.expectMessage("Invalid user parameter for password [" + password + "]");
        UserValidator.validatePassword(password);
    }

    @Test
    public void passwordValidator_noLowerCase() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);

        String password = "12AZER,;";
        exception.expectMessage("Invalid user parameter for password [" + password + "]");
        UserValidator.validatePassword(password);
    }

    @Test
    public void passwordValidator_noSpecialCharacter() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);

        String password = "1234AZer";
        exception.expectMessage("Invalid user parameter for password [" + password + "]");
        UserValidator.validatePassword(password);
    }

    @Test
    public void passwordValidator_isValid()
    {
        try {
            UserValidator.validatePassword("12AZer,;");
            assertTrue(true);
        } catch (InvalidUserParameter ignored) {
            fail();
        }
    }
}
