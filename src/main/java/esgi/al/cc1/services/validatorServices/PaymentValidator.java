package esgi.al.cc1.services.validatorServices;

import esgi.al.cc1.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.models.Payment;

public class PaymentValidator
{
    public static void validate(Payment payment) throws InvalidModelParameter
    {
        validateAmount(payment.getAmount());
    }

    public static void validateAmount(float amount) throws InvalidModelParameter
    {
        if (amount <= 0)
        {
            throw new InvalidModelParameter("amount", Float.toString(amount));
        }
    }
}
