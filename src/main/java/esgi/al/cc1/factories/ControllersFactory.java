package esgi.al.factories;

import esgi.al.controllers.Controller;
import esgi.al.controllers.PaymentController;
import esgi.al.controllers.UserController;
import esgi.al.models.Payment;
import esgi.al.models.User;

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
