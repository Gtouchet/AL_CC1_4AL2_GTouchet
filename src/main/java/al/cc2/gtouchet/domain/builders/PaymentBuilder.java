package al.cc2.gtouchet.domain.builders;

import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;

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
    public Payment build() throws NullPointerException
    {
        return Payment.of(
                Objects.requireNonNull(this.id),
                Objects.requireNonNull(this.contractorId),
                Objects.requireNonNull(this.workerId),
                Objects.requireNonNull(this.paymentMethod),
                this.amount,
                Objects.requireNonNull(this.reason),
                Objects.requireNonNull(this.creationDate)
        );
    }

    public static PaymentBuilder init(Id id, Date creationDate)
    {
        return new PaymentBuilder(id, creationDate);
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
