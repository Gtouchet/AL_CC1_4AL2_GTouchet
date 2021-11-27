package esgi.al.cc1.infrastructure.factories;

import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.controllers.Controller;
import esgi.al.cc1.infrastructure.controllers.PaymentController;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.infrastructure.controllers.WorkerController;

public class ControllersFactory
{
    public Controller<Payment> createPaymentController()
    {
        return new PaymentController(new RepositoriesFactory().createPaymentRepository());
    }

    public Controller<Worker> createWorkerHandler()
    {
        return new WorkerController(new RepositoriesFactory().createWorkerRepository());
    }
}
