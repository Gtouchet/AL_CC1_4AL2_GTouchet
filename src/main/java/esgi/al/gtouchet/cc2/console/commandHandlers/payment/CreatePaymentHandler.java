package esgi.al.gtouchet.cc2.console.commandHandlers.payment;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.payment.dtos.CreatePaymentDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class CreatePaymentHandler implements CommandHandler
{
    private final ServiceHandler<Payment, CreatePaymentDto> serviceHandler;

    public CreatePaymentHandler(ServiceHandler<Payment, CreatePaymentDto> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.CREATE_PAYMENT.parameters)
        {
            try {
                Payment payment = this.serviceHandler.handle(new CreatePaymentDto(
                        Id.fromString(params[1].toLowerCase()),
                        Id.fromString(params[2].toLowerCase()),
                        Double.parseDouble(params[3]),
                        params[4]
                ));
                if (payment != null)
                {
                    System.out.println(payment);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: could not parse [" + params[3] + "] as an amount");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.CREATE_PAYMENT);
        }
    }
}
