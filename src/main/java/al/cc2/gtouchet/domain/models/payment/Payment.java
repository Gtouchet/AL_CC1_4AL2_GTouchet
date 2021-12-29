package al.cc2.gtouchet.domain.models.payment;

import al.cc2.gtouchet.domain.models.Entity;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class Payment extends Entity
{
    private final EntityId contractorId;
    private final EntityId workerId;
    private final PaymentMethod paymentMethod;
    private final double amount;
    private final String reason;

    private Payment(EntityId id, EntityId contractorId, EntityId workerId, PaymentMethod paymentMethod,
                    double amount, String reason, Clock creationClock)
    {
        super(id, creationClock);

        this.contractorId = contractorId;
        this.workerId = workerId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.reason = reason;
    }

    public static Payment of(EntityId id, EntityId contractorId, EntityId workerId, PaymentMethod paymentMethod,
                             double amount, String reason, Clock creationClock)
    {
        return new Payment(id, contractorId, workerId, paymentMethod, amount, reason, creationClock);
    }

    public EntityId getContractorId()
    {
        return this.contractorId;
    }

    public EntityId getWorkerId()
    {
        return this.workerId;
    }

    public PaymentMethod getPaymentMethod()
    {
        return this.paymentMethod;
    }

    public double getAmount()
    {
        return this.amount;
    }

    public String getReason()
    {
        return this.reason;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "\nContractor ID: " + this.contractorId +
                "\nWorker ID: " + this.workerId +
                "\nPaid by: " + this.paymentMethod +
                "\nAmount: " + this.amount +
                "\nReason: " + this.reason +
                "\nPayment date: " + this.getCreationDate();
    }
}
