package esgi.al.cc1.validatorsTests;

import esgi.al.cc1.domain.validators.PasswordValidator;
import esgi.al.cc1.domain.valueObjects.Password;
import esgi.al.cc1.domain.valueObjects.PasswordFormatException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PasswordValidatorTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    public void validPassword() throws PasswordFormatException
    {
        Password password = Password.of("ABcd1234!");

        this.validator.validate(password);
    }

    @Test
    public void invalidPassword_tooShort() throws PasswordFormatException
    {
        Password password = Password.of("Aa1!");

        this.exception.expect(PasswordFormatException.class);
        this.validator.validate(password);
    }

    @Test
    public void invalidPassword_noUpperCase() throws PasswordFormatException
    {
        Password password = Password.of("abcd1234!");

        this.exception.expect(PasswordFormatException.class);
        this.validator.validate(password);
    }

    @Test
    public void invalidPassword_noLowerCase() throws PasswordFormatException
    {
        Password password = Password.of("ABCD1234!");

        this.exception.expect(PasswordFormatException.class);
        this.validator.validate(password);
    }

    @Test
    public void invalidPassword_noSpecialCharacter() throws PasswordFormatException
    {
        Password password = Password.of("ABcd1234");

        this.exception.expect(PasswordFormatException.class);
        this.validator.validate(password);
    }
}
