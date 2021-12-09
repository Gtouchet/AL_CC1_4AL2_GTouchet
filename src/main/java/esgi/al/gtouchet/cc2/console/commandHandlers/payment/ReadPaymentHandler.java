package esgi.al.gtouchet.cc2.console.commandHandlers.payment;

import esgi.al.gtouchet.cc2.application.PaymentService;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class ReadPaymentHandler implements CommandHandler
{
    private final PaymentService paymentService;

    public ReadPaymentHandler(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.readPayment.parameters)
        {
            this.paymentService.read();
        }
        else if (params.length == Command.readPayment.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            this.paymentService.read(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.readPayment);
        }
    }
}