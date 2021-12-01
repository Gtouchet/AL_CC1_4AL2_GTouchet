package esgi.al.cc1.console.commandHandlers.contractor;

import esgi.al.cc1.application.ContractorService;
import esgi.al.cc1.console.commandHandlers.CommandHandler;
import esgi.al.cc1.console.engine.Command;
import esgi.al.cc1.console.engine.WrongNumberOfArgumentException;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.valueObjects.Password;

public class CreateContractorHandler implements CommandHandler
{
    private final ContractorService contractorService;

    public CreateContractorHandler(ContractorService contractorService)
    {
        this.contractorService = contractorService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.createContractor.parameters)
        {
            try {
                this.contractorService.create(
                        params[1],
                        Password.of(params[2]),
                        params[3],
                        PaymentMethod.valueOf(params[4].toLowerCase())
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Error: unknown payment method [" + params[4] + "]");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.createContractor);
        }
    }
}
