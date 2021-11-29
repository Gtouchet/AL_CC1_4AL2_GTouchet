package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.dtos.Date;
import esgi.al.cc1.domain.dtos.Id;
import esgi.al.cc1.domain.enumerators.PaymentMethod;

public class Payment
{
    private final Id id;
    private final Id contractorId;
    private final Id workerId;
    private final PaymentMethod paymentMethod;
    private final float amount;

    private final Date creationDate;

    private Payment(Id contractorId, Id workerId, PaymentMethod paymentMethod, float amount)
    {
        this.id = Id.generate();
        this.contractorId = contractorId;
        this.workerId = workerId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;

        this.creationDate = Date.now();
    }

    public static Payment of(Id contractorId, Id workerId, PaymentMethod paymentMethod, float amount)
    {
        return new Payment(contractorId, workerId, paymentMethod, amount);
    }

    public static Payment of(String contractorId, String workerId, PaymentMethod paymentMethod, float amount)
    {
        return new Payment(Id.set(contractorId), Id.set(workerId), paymentMethod, amount);
    }

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

    public float getAmount()
    {
        return this.amount;
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
                "\nPayment date: " + this.creationDate;
    }
}
