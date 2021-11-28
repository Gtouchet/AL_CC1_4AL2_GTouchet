package esgi.al.cc1.application.commandHandlers.payment;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.infrastructure.controllers.Controller;
import esgi.al.cc1.domain.models.Payment;

public class CreatePaymentHandler implements CommandHandler
{
    private final Controller<Payment> paymentController;

    public CreatePaymentHandler(Controller<Payment> paymentController)
    {
        this.paymentController = paymentController;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgument
    {

    }
}
