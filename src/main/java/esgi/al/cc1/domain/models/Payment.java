package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;

public class Payment implements Entity
{
    private final Id id;
    private final Id contractorId;
    private final Id workerId;
    private final PaymentMethod paymentMethod;
    private final double amount;
    private final String reason;

    private final Date creationDate;

    private Payment(Id id, Id contractorId, Id workerId, PaymentMethod paymentMethod,
                    double amount, String reason, Date creationDate)
    {
        this.id = id;
        this.contractorId = contractorId;
        this.workerId = workerId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.reason = reason;

        this.creationDate = creationDate;
    }

    public static Payment of(Id id, Id contractorId, Id workerId, PaymentMethod paymentMethod,
                             double amount, String reason, Date creationDate)
    {
        return new Payment(id, contractorId, workerId, paymentMethod, amount, reason, creationDate);
    }

    @Override
    public Id getId()
    {
        return this.id;
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

    public Date getCreationDate()
    {
        return this.creationDate;
    }

    @Override
    public String toString()
    {
        return "ID: " + this.id +
                "\nContractor ID: " + this.contractorId +
                "\nWorker ID: " + this.workerId +
                "\nPaid by: " + this.paymentMethod +
                "\nAmount: " + this.amount +
                "\nReason: " + this.reason +
                "\nPayment date: " + this.creationDate;
    }
}
