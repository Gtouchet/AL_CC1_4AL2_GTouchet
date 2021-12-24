package al.cc2.gtouchet.application.services.dtos.payment;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class DeletePaymentCommand implements Command
{
    public final Id id;

    public DeletePaymentCommand(Id id)
    {
        this.id = id;
    }
}
