package esgi.al.cc1.console.commandHandlers.contractor;

import esgi.al.cc1.application.ContractorService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.valueObjects.Id;

public class ValidatePaymentHandler implements CommandHandler
{
    private final ContractorService contractorService;

    public ValidatePaymentHandler(ContractorService contractorService)
    {
        this.contractorService = contractorService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.validatePayment.parameters)
        {
            this.contractorService.validatePayment(
                    Id.fromString(params[1].toLowerCase())
            );
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.validatePayment);
        }
    }
}