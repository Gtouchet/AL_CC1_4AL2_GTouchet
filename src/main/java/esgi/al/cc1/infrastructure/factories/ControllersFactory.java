package esgi.al.cc1.infrastructure.factories;

import esgi.al.cc1.infrastructure.controllers.Controller;
import esgi.al.cc1.infrastructure.controllers.PaymentController;
import esgi.al.cc1.domain.models.Payment;

public class ControllersFactory
{
    public Controller<Payment> createPaymentController()
    {
        return new PaymentController(new RepositoriesFactory().createPaymentRepository());
    }
}
