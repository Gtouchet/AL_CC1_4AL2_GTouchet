package esgi.al.gtouchet.cc2.console.commandHandlers.contractor;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.application.contractorServices.create.CreateContractorDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

import java.util.List;

public class ReadContractorCommandHandler implements CommandHandler
{
    private final ServiceHandler<List<Contractor>, Id> serviceHandler;

    public ReadContractorCommandHandler(ServiceHandler<List<Contractor>, Id> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.READ_CONTRACTOR.parameters)
        {
            this.serviceHandler.handle(null).forEach(System.out::println);
        }
        else if (params.length == Command.READ_CONTRACTOR.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            System.out.println(this.serviceHandler.handle(
                    Id.fromString(params[1].toLowerCase())
            ));
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.READ_CONTRACTOR);
        }
    }
}
