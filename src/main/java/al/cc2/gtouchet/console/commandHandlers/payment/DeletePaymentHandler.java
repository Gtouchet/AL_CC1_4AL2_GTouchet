package al.cc2.gtouchet.console.commandHandlers.payment;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.Objects;

public class DeletePaymentHandler implements CommandHandler
{
    private final ServiceHandler<Boolean, Id> serviceHandler;

    public DeletePaymentHandler(ServiceHandler<Boolean, Id> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.DELETE_PAYMENT.parameters)
        {
            boolean success = this.serviceHandler.handle(
                    Id.fromString(params[1].toLowerCase())
            );
            if (success)
            {
                System.out.println("Payment ID " + params[1].toLowerCase() + " deleted");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.DELETE_PAYMENT);
        }
    }
}
