package al.cc2.gtouchet.console.handlers.contractor;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.UpdateContractorCommand;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.payment.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.domain.valueObjects.Password;

import java.util.Objects;

public final class UpdateContractorConsoleHandler implements ConsoleHandler
{
    private final CommandHandler<Contractor, UpdateContractorCommand> commandHandler;

    public UpdateContractorConsoleHandler(CommandHandler commandHandler) throws NullPointerException
    {
        this.commandHandler = Objects.requireNonNull(commandHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.UPDATE_CONTRACTOR.parameters)
        {
            try {
                Contractor contractor = this.commandHandler.handle(new UpdateContractorCommand(
                        Id.fromString(params[1].toLowerCase()),
                        Password.of(params[2]),
                        params[3],
                        PaymentMethod.valueOf(params[4].toUpperCase())
                ));
                if (contractor != null)
                {
                    System.out.println(contractor);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: unknown payment method [" + params[5] + "]");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.UPDATE_CONTRACTOR);
        }
    }
}
