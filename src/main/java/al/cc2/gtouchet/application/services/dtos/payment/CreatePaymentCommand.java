package al.cc2.gtouchet.application.services.dtos.payment;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class CreatePaymentCommand implements Command
{
    public final Id contractorId;
    public final Id workerId;
    public final double amount;
    public final String reason;

    public CreatePaymentCommand(
            Id contractorId,
            Id workerId,
            double amount,
            String reason)
    {
        this.contractorId = contractorId;
        this.workerId = workerId;
        this.amount = amount;
        this.reason = reason;
    }
}
