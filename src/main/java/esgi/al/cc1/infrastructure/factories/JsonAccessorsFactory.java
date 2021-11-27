package esgi.al.cc1.infrastructure.factories;

import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonDataAccessor;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonPathCreator;

public class JsonAccessorsFactory
{
    public JsonDataAccessor<Payment> createPaymentJsonAccessor()
    {
        return new JsonDataAccessor<>(Payment.class, new JsonPathCreator(
                "./res/payments.json",
                "./res/backups/payments/"
        ));
    }

    public JsonDataAccessor<Worker> createWorkerJsonAccessor()
    {
        return new JsonDataAccessor<>(Worker.class, new JsonPathCreator(
                "./res/workers.json",
                "./res/backups/workers/"
        ));
    }
}
