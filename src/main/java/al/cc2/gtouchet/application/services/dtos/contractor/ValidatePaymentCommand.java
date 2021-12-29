package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class ValidatePaymentCommand implements Command
{
    public final EntityId id;

    public ValidatePaymentCommand(EntityId id)
    {
        this.id = id;
    }
}
