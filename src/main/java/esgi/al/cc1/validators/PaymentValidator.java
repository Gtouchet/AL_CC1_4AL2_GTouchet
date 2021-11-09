package esgi.al.validators;

import esgi.al.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.models.Payment;

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
