package esgi.al.userValidatorTests;

import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
import esgi.al.validators.UserValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LoginValidatorTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void loginValidator_loginNull() throws InvalidUserParameter
    {
        exception.expect(NullPointerException.class);

        UserValidator.validateLogin(null);
    }

    @Test
    public void loginValidator_loginEmpty() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);

        String login = "";
        exception.expectMessage("Invalid user parameter for login [" + login + "]");
        UserValidator.validateLogin(login);
    }

    @Test
    public void loginValidator_loginOnlySpaces() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);

        String login = "     ";
        exception.expectMessage("Invalid user parameter for login [" + login + "]");
        UserValidator.validateLogin(login);
    }

    @Test
    public void loginValidator_isValid()
    {
        try {
            UserValidator.validateLogin("GTouchet123");
            assertTrue(true);
        } catch (InvalidUserParameter ignored) {
            fail();
        }
    }

    @Test
    public void loginValidator_isValidWithLotsOfSpaces()
    {
        try {
            UserValidator.validateLogin("     GTouchet123     ");
            assertTrue(true);
        } catch (InvalidUserParameter ignored) {
            fail();
        }
    }
}
