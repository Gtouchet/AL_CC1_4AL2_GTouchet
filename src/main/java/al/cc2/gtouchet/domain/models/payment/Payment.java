package al.cc2.gtouchet.domain.models.payment;

import al.cc2.gtouchet.domain.models.Entity;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class Payment extends Entity
{
    private final Id contractorId;
    private final Id workerId;
    private final PaymentMethod paymentMethod;
    private final double amount;
    private final String reason;

    private Payment(Id id, Id contractorId, Id workerId, PaymentMethod paymentMethod,
                    double amount, String reason, Date creationDate)
    {
        super(id, creationDate);

        this.contractorId = contractorId;
        this.workerId = workerId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.reason = reason;
    }

    public static Payment of(Id id, Id contractorId, Id workerId, PaymentMethod paymentMethod,
                             double amount, String reason, Date creationDate)
    {
        return new Payment(id, contractorId, workerId, paymentMethod, amount, reason, creationDate);
    }

    public Id getContractorId()
    {
        return this.contractorId;
    }

    public Id getWorkerId()
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
