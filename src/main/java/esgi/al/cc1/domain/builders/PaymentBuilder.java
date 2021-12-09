package esgi.al.cc1.domain.builders;

import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;

import java.util.Objects;

public class PaymentBuilder implements Builder<Payment>
{
    private final Id id;
    private Id contractorId;
    private Id workerId;
    private PaymentMethod paymentMethod;
    private double amount;
    private String reason;
    private final Date creationDate;

    private PaymentBuilder(Id id, Date creationDate)
    {
        this.id = id;
        this.creationDate = creationDate;
    }

    @Override
    public Payment build()
    {
        return Payment.of(
                this.id,
                Objects.requireNonNull(this.contractorId),
                Objects.requireNonNull(this.workerId),
                Objects.requireNonNull(this.paymentMethod),
                this.amount,
                Objects.requireNonNull(this.reason),
                this.creationDate
        );
    }

    public static PaymentBuilder init(Id id, Date creationDate)
    {
        return new PaymentBuilder(
                Objects.requireNonNull(id),
                Objects.requireNonNull(creationDate)
        );
    }

    public PaymentBuilder setContractorId(Id contractorId)
    {
        PaymentBuilder builder = this;
        builder.contractorId = contractorId;
        return builder;
    }

    public PaymentBuilder setWorkerId(Id workerId)
    {
        PaymentBuilder builder = this;
        builder.workerId = workerId;
        return builder;
    }

    public PaymentBuilder setPaymentMethod(PaymentMethod paymentMethod)
    {
        PaymentBuilder builder = this;
        builder.paymentMethod = paymentMethod;
        return builder;
    }

    public PaymentBuilder setAmount(double amount)
    {
        PaymentBuilder builder = this;
        builder.amount = amount;
        return builder;
    }

    public PaymentBuilder setReason(String reason)
    {
        PaymentBuilder builder = this;
        builder.reason = reason;
        return builder;
    }
}
