package al.cc2.gtouchet.console.handlers.payment;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.payment.CreatePaymentCommand;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.payment.Payment;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.Objects;

public final class CreatePaymentConsoleHandler implements ConsoleHandler
{
    private final CommandHandler<Payment, CreatePaymentCommand> commandHandler;

    public CreatePaymentConsoleHandler(CommandHandler commandHandler) throws NullPointerException
    {
        this.commandHandler = Objects.requireNonNull(commandHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.CREATE_PAYMENT.parameters)
        {
            try {
                Payment payment = this.commandHandler.handle(new CreatePaymentCommand(
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
            throw new WrongNumberOfArgumentException(ConsoleCommand.CREATE_PAYMENT);
        }
    }
}
