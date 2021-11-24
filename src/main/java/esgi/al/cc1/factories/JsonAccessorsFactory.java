package esgi.al.cc1.factories;

import esgi.al.cc1.models.Payment;
import esgi.al.cc1.models.User;
import esgi.al.cc1.services.jsonServices.JsonAccessor;
import esgi.al.cc1.services.jsonServices.JsonPathCreator;

public class JsonAccessorsFactory
{
    public JsonAccessor<User> createUserJsonAccessor()
    {
        return new JsonAccessor<>(User.class, new JsonPathCreator(
                "./res/registeredUsers.json",
                "./res/backups/user/"
        ));
    }

    public JsonAccessor<Payment> createPaymentJsonAccessor()
    {
        return new JsonAccessor<>(Payment.class, new JsonPathCreator(
                "./res/registeredPayments.json",
                "./res/backups/payment/"
        ));
    }
}
