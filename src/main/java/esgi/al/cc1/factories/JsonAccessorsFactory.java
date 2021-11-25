package esgi.al.cc1.factories;

import esgi.al.cc1.models.Payment;
import esgi.al.cc1.models.Tradesman;
import esgi.al.cc1.services.jsonServices.JsonAccessor;
import esgi.al.cc1.services.jsonServices.JsonPathCreator;

public class JsonAccessorsFactory
{
    public JsonAccessor<Tradesman> createUserJsonAccessor()
    {
        return new JsonAccessor<>(Tradesman.class, new JsonPathCreator(
                "./res/tradesmen.json",
                "./res/backups/user/"
        ));
    }

    public JsonAccessor<Payment> createPaymentJsonAccessor()
    {
        return new JsonAccessor<>(Payment.class, new JsonPathCreator(
                "./res/payments.json",
                "./res/backups/payment/"
        ));
    }
}
