package al.cc2.gtouchet.application.services.dtos.payment;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class DeletePaymentCommand implements Command
{
    public final EntityId id;

    public DeletePaymentCommand(EntityId id)
    {
        this.id = id;
    }
}
