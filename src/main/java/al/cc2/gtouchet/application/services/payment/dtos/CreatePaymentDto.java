package al.cc2.gtouchet.application.services.payment.dtos;

import al.cc2.gtouchet.domain.valueObjects.Id;

public class CreatePaymentDto
{
    public final Id contractorId;
    public final Id workerId;
    public final double amount;
    public final String reason;

    public CreatePaymentDto(
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
