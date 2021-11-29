package esgi.al.cc1.infrastructure.factories;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Project;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonDataAccessor;

public class JsonAccessorsFactory
{
    public JsonDataAccessor<Contractor> createContractorJsonAccessor()
    {
        return new JsonDataAccessor<>(Contractor.class);
    }

    public JsonDataAccessor<Payment> createPaymentJsonAccessor()
    {
        return new JsonDataAccessor<>(Payment.class);
    }

    public JsonDataAccessor<Project> createProjectJsonAccessor()
    {
        return new JsonDataAccessor<>(Project.class);
    }

    public JsonDataAccessor<Worker> createWorkerJsonAccessor()
    {
        return new JsonDataAccessor<>(Worker.class);
    }
}
