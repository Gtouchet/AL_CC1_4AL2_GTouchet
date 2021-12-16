package esgi.al.gtouchet.cc2.console.commandHandlers.contractor;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.create.CreateContractorDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

public class CreateContractorCommandHandler implements CommandHandler
{
    private final ServiceHandler<Contractor, CreateContractorDto> serviceHandler;

    public CreateContractorCommandHandler(ServiceHandler<Contractor, CreateContractorDto> serviceHandler)
    {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.CREATE_CONTRACTOR.parameters)
        {
            try {
                Contractor contractor = this.serviceHandler.handle(new CreateContractorDto(
                        params[1],
                        Password.of(params[2]),
                        params[3],
                        PaymentMethod.valueOf(params[4].toLowerCase())
                ));
                if (contractor != null)
                {
                    System.out.println(contractor);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: unknown payment method [" + params[4] + "]");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(Command.CREATE_CONTRACTOR);
        }
    }
}
