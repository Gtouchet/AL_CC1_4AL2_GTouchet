package esgi.al.gtouchet.cc2.console.commandHandlers.payment;

import esgi.al.gtouchet.cc2.application.paymentService.PaymentService;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

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
        if (params.length == Command.DELETE_PAYMENT.parameters)
        {
            this.paymentService.delete(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.DELETE_PAYMENT);
        }
    }
}
