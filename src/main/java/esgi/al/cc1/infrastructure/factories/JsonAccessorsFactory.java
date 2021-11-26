package esgi.al.cc1.infrastructure.factories;

import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonAccessor;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonPathCreator;

public class JsonAccessorsFactory
{
    public JsonAccessor<Contractor> createUserJsonAccessor()
    {
        return new JsonAccessor<>(Contractor.class, new JsonPathCreator(
                "./res/contractors.json",
                "./res/backups/contractors/"
        ));
    }

    public JsonAccessor<Payment> createPaymentJsonAccessor()
    {
        return new JsonAccessor<>(Payment.class, new JsonPathCreator(
                "./res/payments.json",
                "./res/backups/payments/"
        ));
    }
}
