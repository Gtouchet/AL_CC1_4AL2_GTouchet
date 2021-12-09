package esgi.al.gtouchet.cc2.infrastructure.apis;

import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;

public class PaymentMethodValidatorApi
{
    public void validate(PaymentMethod paymentMethod) throws PaymentMethodValidationException
    {
        switch (paymentMethod)
        {
            case paypal:
                System.out.println("Calling Paypal's validation service...\nValidated");
                break;
            case card:
                System.out.println("Checking card's credentials...\nValidated");
                break;
            case transfer:
                System.out.println("Calling the contractor's bank API...\nValidated");
                break;
            default:
                System.out.println("Error: impossible to validate the payment method");
                throw new PaymentMethodValidationException(paymentMethod);
        }
    }
}
