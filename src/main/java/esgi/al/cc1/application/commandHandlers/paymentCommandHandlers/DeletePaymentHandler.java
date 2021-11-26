package esgi.al.cc1.application.commandHandlers.paymentCommandHandlers;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.controllers.Controller;
import esgi.al.cc1.domain.models.Payment;

public class DeletePaymentHandler implements CommandHandler
{
    private final Controller<Payment> paymentController;

    public DeletePaymentHandler(Controller<Payment> paymentController)
    {
        this.paymentController = paymentController;
    }

    @Override
    public void handle(String[] params)
    {
        System.out.println("On est bien dans le DELETE payment handler");
    }
}
