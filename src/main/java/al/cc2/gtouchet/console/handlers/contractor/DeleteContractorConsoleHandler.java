package al.cc2.gtouchet.console.handlers.contractor;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.DeleteContractorCommand;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

import java.util.Objects;

public final class DeleteContractorConsoleHandler implements ConsoleHandler
{
    private final CommandHandler<Boolean, DeleteContractorCommand> commandHandler;

    public DeleteContractorConsoleHandler(CommandHandler commandHandler) throws NullPointerException
    {
        this.commandHandler = Objects.requireNonNull(commandHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.DELETE_CONTRACTOR.parameters)
        {
            boolean success = this.commandHandler.handle(new DeleteContractorCommand(
                    EntityId.fromString(params[1].toLowerCase())
            ));
            if (success)
            {
                System.out.println("Contractor ID " + params[1].toLowerCase() + " deleted");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.DELETE_CONTRACTOR);
        }
    }
}
