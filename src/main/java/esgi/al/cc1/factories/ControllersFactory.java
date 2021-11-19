package esgi.al.cc1.factories;

import esgi.al.cc1.controllers.Controller;
import esgi.al.cc1.controllers.PaymentController;
import esgi.al.cc1.controllers.UserController;
import esgi.al.cc1.models.Payment;
import esgi.al.cc1.models.User;

public class ControllersFactory
{
    public Controller<User> createUserController()
    {
        return new UserController(new RepositoriesFactory().createUserRepository());
    }

    public Controller<Payment> createPaymentController()
    {
        return new PaymentController(new RepositoriesFactory().createPaymentController());
    }
}
