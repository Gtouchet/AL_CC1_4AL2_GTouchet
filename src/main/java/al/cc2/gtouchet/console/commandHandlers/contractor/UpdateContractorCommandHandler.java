package al.cc2.gtouchet.console.commandHandlers.contractor;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.contractor.dtos.UpdateContractorDto;
import al.cc2.gtouchet.console.commandHandlers.CommandHandler;
import al.cc2.gtouchet.console.engine.Command;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.domain.valueObjects.Password;

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
