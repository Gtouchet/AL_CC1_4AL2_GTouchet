package esgi.al.userValidatorTests;

import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
import esgi.al.validators.UserValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PaymentMethodValidatorTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void paymentMethodValidator_paymentMethodNull() throws InvalidUserParameter
    {
        exception.expect(NullPointerException.class);

        UserValidator.validatePaymentMethod(null);
    }

    @Test
    public void paymentMethodValidator_paymentMethodUnknown() throws InvalidUserParameter
    {
        exception.expect(InvalidUserParameter.class);

        String paymentMethod = "bitcoin";
        exception.expectMessage("Invalid user parameter for payment method [" + paymentMethod + "]");
        UserValidator.validatePaymentMethod(paymentMethod);
    }

    @Test
    public void paymentMethodValidator_isValidCard()
    {
        try {
            UserValidator.validatePaymentMethod("card");
            assertTrue(true);
        } catch (InvalidUserParameter ignored) {
            fail();
        }
    }

    @Test
    public void paymentMethodValidator_isValidPaypal()
    {
        try {
            UserValidator.validatePaymentMethod("paypal");
            assertTrue(true);
        } catch (InvalidUserParameter ignored) {
            fail();
        }
    }
}
