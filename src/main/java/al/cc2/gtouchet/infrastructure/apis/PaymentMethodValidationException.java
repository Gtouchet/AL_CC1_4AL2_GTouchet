package al.cc2.gtouchet.infrastructure.apis;

import al.cc2.gtouchet.domain.models.PaymentMethod;

public class PaymentMethodValidationException extends Exception
{
    public PaymentMethodValidationException(PaymentMethod paymentMethod)
    {
        super("Error: impossible to validate payment method [" + paymentMethod + "]");
    }
}
