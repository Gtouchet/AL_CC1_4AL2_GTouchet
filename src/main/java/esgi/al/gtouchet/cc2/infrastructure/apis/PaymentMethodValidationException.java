package esgi.al.gtouchet.cc2.infrastructure.apis;

import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;

public class PaymentMethodValidationException extends Exception
{
    public PaymentMethodValidationException(PaymentMethod paymentMethod)
    {
        super("Error: impossible to validate payment method [" + paymentMethod + "]");
    }
}
