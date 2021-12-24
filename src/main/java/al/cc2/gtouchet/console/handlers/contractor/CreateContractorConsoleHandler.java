package al.cc2.gtouchet.console.handlers.contractor;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.CreateContractorCommand;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Password;

import java.util.Objects;

public class CreateContractorConsoleHandler implements ConsoleHandler
{
    private final CommandHandler<Contractor, CreateContractorCommand> commandHandler;

    public CreateContractorConsoleHandler(CommandHandler commandHandler) throws NullPointerException
    {
        this.commandHandler = Objects.requireNonNull(commandHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.CREATE_CONTRACTOR.parameters)
        {
            try {
                Contractor contractor = this.commandHandler.handle(new CreateContractorCommand(
                        params[1],
                        Password.of(params[2]),
                        params[3],
                        PaymentMethod.valueOf(params[4].toLowerCase())
                ));
                if (contractor != null)
                {
                    System.out.println(contractor);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: unknown payment method [" + params[4] + "]");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.CREATE_CONTRACTOR);
        }
    }
}
