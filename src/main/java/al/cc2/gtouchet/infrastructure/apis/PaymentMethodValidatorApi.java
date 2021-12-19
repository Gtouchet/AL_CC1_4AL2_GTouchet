package al.cc2.gtouchet.infrastructure.apis;

import al.cc2.gtouchet.domain.models.PaymentMethod;

public class PaymentMethodValidatorApi
{
    //private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    public void validate(PaymentMethod paymentMethod) throws PaymentMethodValidationException
    {
        switch (paymentMethod)
        {
            case paypal:
                System.out.println("Calling Paypal's validation service... Validated");
                //this.logger.log(Level.INFO, this.logger.getName() + " Calling Paypal's validation service... Validated");
                break;
            case card:
                System.out.println("Checking card's credentials... Validated");
                //this.logger.log(Level.INFO, this.logger.getName() + " Checking card's credentials... Validated");
                break;
            case transfer:
                System.out.println("Calling the contractor's bank API... Validated");
                //this.logger.log(Level.INFO, this.logger.getName() + " Calling the contractor's bank API... Validated");
                break;
            default:
                throw new PaymentMethodValidationException(paymentMethod);
        }
    }
}
