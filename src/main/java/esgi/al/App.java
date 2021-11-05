package esgi.al;

import esgi.al.cliInterpreter.CliInterpreterEngine;
import esgi.al.controllers.Controller;
import esgi.al.factories.ControllersFactory;
import esgi.al.models.Payment;
import esgi.al.models.User;

public class App
{
    public static void main(String[] args)
    {
        final Controller<User> userController = new ControllersFactory().createUserController();
        final Controller<Payment> paymentController = new ControllersFactory().createPaymentController();

        new CliInterpreterEngine(userController, paymentController).launch();
    }
}
