package esgi.al.cc1.console.commandHandlers.payment;

import esgi.al.cc1.application.PaymentService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.valueObjects.Id;

public class DeletePaymentHandler implements CommandHandler
{
    private final PaymentService paymentService;

    public DeletePaymentHandler(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.deletePayment.parameters)
        {
            this.paymentService.delete(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.deletePayment);
        }
    }
}
