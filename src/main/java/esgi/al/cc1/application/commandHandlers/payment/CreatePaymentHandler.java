package esgi.al.cc1.application.commandHandlers.payment;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.infrastructure.controllers.Controller;

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
        if (params.length == Command.createPayment.parameters)
        {
            this.paymentController.create(params);
        }
        else
        {
            throw new WrongNumberOfArgument(Command.createPayment);
        }
    }
}
