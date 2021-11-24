package esgi.al.cc1.userValidatorTests;

import esgi.al.cc1.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.services.validatorServices.UserValidator;
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
    public void paymentMethodValidator_paymentMethodNull() throws InvalidModelParameter
    {
        exception.expect(NullPointerException.class);

        UserValidator.validatePaymentMethod(null);
    }

    @Test
    public void paymentMethodValidator_paymentMethodUnknown() throws InvalidModelParameter
    {
        exception.expect(InvalidModelParameter.class);

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
        } catch (InvalidModelParameter ignored) {
            fail();
        }
    }

    @Test
    public void paymentMethodValidator_isValidPaypal()
    {
        try {
            UserValidator.validatePaymentMethod("paypal");
            assertTrue(true);
        } catch (InvalidModelParameter ignored) {
            fail();
        }
    }
}
