package al.cc2.gtouchet.infrastructure.apis;

import al.cc2.gtouchet.domain.models.payment.PaymentMethod;

public class PaymentMethodValidationException extends RuntimeException
{
    public PaymentMethodValidationException(PaymentMethod paymentMethod)
    {
        super("Error: impossible to validate payment method [" + paymentMethod + "]");
    }
}
