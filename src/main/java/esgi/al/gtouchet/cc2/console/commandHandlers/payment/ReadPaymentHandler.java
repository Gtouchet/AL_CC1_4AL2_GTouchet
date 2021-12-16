package esgi.al.gtouchet.cc2.console.commandHandlers.payment;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

import java.util.List;

public class ReadPaymentHandler implements CommandHandler
{
    private final ServiceHandler<List<Payment>, Void> serviceHandlerAll;
    private final ServiceHandler<Payment, Id> serviceHandlerId;

    public ReadPaymentHandler(
            ServiceHandler<List<Payment>, Void> serviceHandlerAll,
            ServiceHandler<Payment, Id> serviceHandlerId)
    {
        this.serviceHandlerAll = serviceHandlerAll;
        this.serviceHandlerId = serviceHandlerId;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.READ_PAYMENT.parameters)
        {
            List<Payment> payments = this.serviceHandlerAll.handle(null);
            if (payments.size() > 0)
            {
                payments.forEach(System.out::println);
            }
            else
            {
                System.out.println("No Contractor registered yet");
            }
        }
        else if (params.length == Command.READ_PAYMENT.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            Payment payment = this.serviceHandlerId.handle(Id.fromString(params[1].toLowerCase()));
            if (payment != null)
            {
                System.out.println(payment);
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.READ_PAYMENT);
        }
    }
}