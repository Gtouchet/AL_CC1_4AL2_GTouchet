package esgi.al.cc1.infrastructure.apis;

import esgi.al.cc1.domain.models.PaymentMethod;

public class PaymentMethodValidatorApi
{
    public boolean validate(PaymentMethod paymentMethod)
    {
        switch (paymentMethod)
        {
            case paypal:
                System.out.println("Calling Paypal's validation service...\nValidated");
                return true;
            case card:
                System.out.println("Checking card's credentials...\nValidated");
                return true;
            case transfer:
                System.out.println("Calling the contractor's bank API...\nValidated");
                return true;
            default:
                System.out.println("Error: impossible to validate the payment method");
                return false;
        }
    }
}
