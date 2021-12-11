package esgi.al.gtouchet.cc2.console.commandHandlers.contractor;

import esgi.al.gtouchet.cc2.application.ContractorService;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;

public class ReadContractorHandler implements CommandHandler
{
    private final ContractorService contractorService;

    public ReadContractorHandler(ContractorService contractorService)
    {
        this.contractorService = contractorService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.READ_CONTRACTOR.parameters)
        {
            this.contractorService.read();
        }
        else if (params.length == Command.READ_CONTRACTOR.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            this.contractorService.read(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.READ_CONTRACTOR);
        }
    }
}
