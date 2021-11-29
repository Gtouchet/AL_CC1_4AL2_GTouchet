package esgi.al.cc1.application.commandHandlers.payment;

import esgi.al.cc1.application.commandHandlers.CommandHandler;
import esgi.al.cc1.application.enumerators.Command;
import esgi.al.cc1.application.exceptions.WrongNumberOfArgument;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.infrastructure.controllers.Controller;

public class DeletePaymentHandler implements CommandHandler
{
    private final Controller<Payment> paymentController;

    public DeletePaymentHandler(Controller<Payment> paymentController)
    {
        this.paymentController = paymentController;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgument
    {
        if (params.length == Command.deletePayment.parameters)
        {
            this.paymentController.remove(params[1]);
        }
        else
        {
            throw new WrongNumberOfArgument(Command.deletePayment);
        }
    }
}
