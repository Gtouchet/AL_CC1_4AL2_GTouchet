package esgi.al.cc1.infrastructure.services.validatorServices;

import esgi.al.cc1.infrastructure.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.domain.models.Payment;

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