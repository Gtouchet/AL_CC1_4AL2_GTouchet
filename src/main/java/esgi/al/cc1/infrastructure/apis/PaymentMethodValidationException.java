package esgi.al.cc1.infrastructure.apis;

import esgi.al.cc1.domain.models.PaymentMethod;

public class PaymentMethodValidationException extends Exception
{
    public PaymentMethodValidationException(PaymentMethod paymentMethod)
    {
        super("Error: impossible to validate payment method [" + paymentMethod + "]");
    }
}
