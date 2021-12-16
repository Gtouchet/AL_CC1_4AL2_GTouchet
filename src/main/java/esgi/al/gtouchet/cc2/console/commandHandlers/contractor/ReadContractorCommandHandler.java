package esgi.al.gtouchet.cc2.console.commandHandlers.contractor;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

import java.util.List;

public class ReadContractorCommandHandler implements CommandHandler
{
    private final ServiceHandler<List<Contractor>, Void> serviceHandlerAll;
    private final ServiceHandler<Contractor, Id> serviceHandlerId;

    public ReadContractorCommandHandler(
            ServiceHandler<List<Contractor>, Void> serviceHandlerAll,
            ServiceHandler<Contractor, Id> serviceHandlerId)
    {
        this.serviceHandlerAll = serviceHandlerAll;
        this.serviceHandlerId = serviceHandlerId;
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
