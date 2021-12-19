package al.cc2.gtouchet.console.commandHandlers.payment;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.payment.dtos.CreatePaymentDto;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.Objects;

public class CreatePaymentHandler implements CommandHandler
{
    private final ServiceHandler<Payment, CreatePaymentDto> serviceHandler;

    public CreatePaymentHandler(ServiceHandler<Payment, CreatePaymentDto> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
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
