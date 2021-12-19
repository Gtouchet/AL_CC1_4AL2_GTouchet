package al.cc2.gtouchet.console.commandHandlers.payment;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.List;
import java.util.Objects;

public class ReadPaymentHandler implements CommandHandler
{
    private final ServiceHandler<List<Payment>, Void> serviceHandlerAll;
    private final ServiceHandler<Payment, Id> serviceHandlerId;

    public ReadPaymentHandler(
            ServiceHandler<List<Payment>, Void> serviceHandlerAll,
            ServiceHandler<Payment, Id> serviceHandlerId) throws NullPointerException
    {
        this.serviceHandlerAll = Objects.requireNonNull(serviceHandlerAll);
        this.serviceHandlerId = Objects.requireNonNull(serviceHandlerId);
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