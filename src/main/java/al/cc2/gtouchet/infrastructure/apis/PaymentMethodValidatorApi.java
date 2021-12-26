package al.cc2.gtouchet.infrastructure.apis;

import al.cc2.gtouchet.domain.models.PaymentMethod;

public class PaymentMethodValidatorApi
{
    public void validate(PaymentMethod paymentMethod) throws PaymentMethodValidationException
    {
        switch (paymentMethod)
        {
            case paypal:
                System.out.println("Calling Paypal's validation service... Validated");
                break;
            case card:
                System.out.println("Checking card's credentials... Validated");
                break;
            case transfer:
                System.out.println("Calling the contractor's bank API... Validated");
                break;
            default:
                throw new PaymentMethodValidationException(paymentMethod);
        }
    }
}
