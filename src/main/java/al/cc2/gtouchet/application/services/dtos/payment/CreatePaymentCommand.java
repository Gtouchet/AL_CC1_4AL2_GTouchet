package al.cc2.gtouchet.application.services.dtos.payment;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class CreatePaymentCommand implements Command
{
    public final EntityId contractorId;
    public final EntityId workerId;
    public final double amount;
    public final String reason;

    public CreatePaymentCommand(
            EntityId contractorId,
            EntityId workerId,
            double amount,
            String reason)
    {
        this.contractorId = contractorId;
        this.workerId = workerId;
        this.amount = amount;
        this.reason = reason;
    }
}
