package esgi.al.gtouchet.cc2.console.commandHandlers.contractor;

import esgi.al.gtouchet.cc2.application.ContractorService;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

public class UpdateContractorHandler implements CommandHandler
{
    private final ContractorService contractorService;

    public UpdateContractorHandler(ContractorService contractorService)
    {
        this.contractorService = contractorService;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.UPDATE_CONTRACTOR.parameters)
        {
            try {
                this.contractorService.update(
                        Id.fromString(params[1].toLowerCase()),
                        Password.of(params[2]),
                        params[3],
                        PaymentMethod.valueOf(params[4].toLowerCase())
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Error: unknown payment method [" + params[5] + "]");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.UPDATE_CONTRACTOR);
        }
    }
}
