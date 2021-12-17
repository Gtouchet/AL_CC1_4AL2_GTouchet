package esgi.al.gtouchet.cc2.console.commandHandlers.contractor;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.contractor.dtos.UpdateContractorDto;
import esgi.al.gtouchet.cc2.console.commandHandlers.CommandHandler;
import esgi.al.gtouchet.cc2.console.engine.Command;
import esgi.al.gtouchet.cc2.console.engine.WrongNumberOfArgumentException;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

import java.util.Objects;

public class UpdateContractorCommandHandler implements CommandHandler
{
    private final ServiceHandler<Contractor, UpdateContractorDto> serviceHandler;

    public UpdateContractorCommandHandler(ServiceHandler<Contractor, UpdateContractorDto> serviceHandler) throws NullPointerException
    {
        this.serviceHandler = Objects.requireNonNull(serviceHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == Command.UPDATE_CONTRACTOR.parameters)
        {
            try {
                Contractor contractor = this.serviceHandler.handle(new UpdateContractorDto(
                        Id.fromString(params[1].toLowerCase()),
                        Password.of(params[2]),
                        params[3],
                        PaymentMethod.valueOf(params[4].toLowerCase())
                ));
                if (contractor != null)
                {
                    System.out.println(contractor);
                }
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
