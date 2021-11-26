package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.dtos.Date;
import esgi.al.cc1.domain.dtos.Id;

public class Payment
{
    private final Id id;
    private final Id contractorId;
    private final Id workerId;
    private final float amount;

    private final Date creationDate;

    private Payment(Id contractorId, Id workerId, float amount)
    {
        this.id = Id.generate();
        this.contractorId = contractorId;
        this.workerId = workerId;
        this.amount = amount;
        this.creationDate = Date.now();
    }

    public static Payment register(Id contractorId, Id workerId, float amount)
    {
        return new Payment(contractorId, workerId, amount);
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
                "";
    }
}
