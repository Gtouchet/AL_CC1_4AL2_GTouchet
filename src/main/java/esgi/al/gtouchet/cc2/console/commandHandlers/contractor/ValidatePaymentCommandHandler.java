package esgi.al.gtouchet.cc2.console.commandHandlers.contractor;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class ValidatePaymentCommandHandler implements CommandHandler
{
    private final ServiceHandler<Boolean, Id> serviceHandler;

    public ValidatePaymentCommandHandler(ServiceHandler<Boolean, Id> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.VALIDATE_PAYMENT.parameters)
        {
            boolean success = this.serviceHandler.handle(
                    Id.fromString(params[1].toLowerCase())
            );
            if (success)
            {
                System.out.println("Contractor ID " + params[1].toLowerCase() + " payment method validated");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.VALIDATE_PAYMENT);
        }
    }
}