package esgi.al.cc1.console.commandHandlers.contractor;

import esgi.al.cc1.application.ContractorService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.valueObjects.Id;

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
        if (params.length == Command.readContractor.parameters)
        {
            this.contractorService.read();
        }
        else if (params.length == Command.readContractor.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            this.contractorService.read(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.readContractor);
        }
    }
}
