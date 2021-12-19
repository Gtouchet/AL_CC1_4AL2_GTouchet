package al.cc2.gtouchet.console.commandHandlers.contractor;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.contractor.dtos.CreateContractorDto;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Password;

import java.util.Objects;

public class CreateContractorCommandHandler implements CommandHandler
{
    private final ServiceHandler<Contractor, CreateContractorDto> serviceHandler;

    public CreateContractorCommandHandler(ServiceHandler<Contractor, CreateContractorDto> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
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
