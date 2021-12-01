package esgi.al.cc1.console.commandHandlers.contractor;

import esgi.al.cc1.application.ContractorService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.valueObjects.Id;

public class DeleteContractorHandler implements CommandHandler
{
    private final ContractorService contractorService;

    public DeleteContractorHandler(ContractorService contractorService)
    {
        this.contractorService = contractorService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.deleteContractor.parameters)
        {
            this.contractorService.delete(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.deleteContractor);
        }
    }
}
