package esgi.al.gtouchet.cc2.console.commandHandlers.payment;

import esgi.al.gtouchet.cc2.application.PaymentService;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class CreatePaymentHandler implements CommandHandler
{
    private final PaymentService paymentService;

    public CreatePaymentHandler(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.createPayment.parameters)
        {
            try {
                this.paymentService.create(
                        Id.fromString(params[1].toLowerCase()),
                        Id.fromString(params[2].toLowerCase()),
                        Double.parseDouble(params[3]),
                        params[4]
                );
            } catch (NumberFormatException e) {
                System.out.println("Error: could not parse [" + params[3] + "] as an amount");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.createPayment);
        }
    }
}
