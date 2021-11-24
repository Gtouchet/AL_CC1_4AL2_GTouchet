package esgi.al.cc1.addressValidatorTests;

import esgi.al.cc1.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.services.validatorServices.AddressValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class NumberValidatorTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void numberValidator_lowerThanZero() throws InvalidModelParameter
    {
        exception.expect(InvalidModelParameter.class);

        int number = -5;
        exception.expectMessage("Invalid address parameter for number [" + number + "]");
        AddressValidator.validateNumber(number);
    }

    @Test
    public void numberValidator_equalZero() throws InvalidModelParameter
    {
        exception.expect(InvalidModelParameter.class);

        int number = 0;
        exception.expectMessage("Invalid address parameter for number [" + number + "]");
        AddressValidator.validateNumber(number);
    }

    @Test
    public void numberValidator_isValid()
    {
        try {
            AddressValidator.validateNumber(18);
            assertTrue(true);
        } catch (InvalidModelParameter e) {
            fail();
        }
    }
}
