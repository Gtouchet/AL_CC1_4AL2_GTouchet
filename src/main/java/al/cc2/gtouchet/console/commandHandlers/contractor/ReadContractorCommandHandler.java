package al.cc2.gtouchet.console.commandHandlers.contractor;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.List;
import java.util.Objects;

public class ReadContractorCommandHandler implements CommandHandler
{
    private final ServiceHandler<List<Contractor>, Void> serviceHandlerAll;
    private final ServiceHandler<Contractor, Id> serviceHandlerId;

    public ReadContractorCommandHandler(
            ServiceHandler<List<Contractor>, Void> serviceHandlerAll,
            ServiceHandler<Contractor, Id> serviceHandlerId) throws NullPointerException
    {
        this.serviceHandlerAll = Objects.requireNonNull(serviceHandlerAll);
        this.serviceHandlerId = Objects.requireNonNull(serviceHandlerId);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.READ_CONTRACTOR.parameters)
        {
            List<Contractor> contractors = this.serviceHandlerAll.handle(null);
            if (contractors.size() > 0)
            {
                contractors.forEach(System.out::println);
            }
            else
            {
                System.out.println("No Contractor registered yet");
            }
        }
        else if (params.length == Command.READ_CONTRACTOR.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            Contractor contractor = this.serviceHandlerId.handle(Id.fromString(params[1].toLowerCase()));
            if (contractor != null)
            {
                System.out.println(contractor);
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.READ_CONTRACTOR);
        }
    }
}
