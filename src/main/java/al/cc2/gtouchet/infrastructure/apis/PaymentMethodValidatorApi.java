package al.cc2.gtouchet.infrastructure.apis;

import al.cc2.gtouchet.domain.models.payment.PaymentMethod;

public final class PaymentMethodValidatorApi
{
    public void validate(PaymentMethod paymentMethod) throws PaymentMethodValidationException
    {
        switch (paymentMethod)
        {
            case PAYPAL:
                System.out.println("Calling Paypal's validation service... Validated");
                break;
            case CARD:
                System.out.println("Checking card's credentials... Validated");
                break;
            case TRANSFER:
                System.out.println("Calling the contractor's bank API... Validated");
                break;
            default:
                throw new PaymentMethodValidationException(paymentMethod);
        }
    }
}
