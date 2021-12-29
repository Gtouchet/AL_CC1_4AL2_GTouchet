package al.cc2.gtouchet.domain.builders;

import al.cc2.gtouchet.domain.models.payment.Payment;
import al.cc2.gtouchet.domain.models.payment.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

import java.util.Objects;

public final class PaymentBuilder implements Builder<Payment>
{
    private final EntityId id;
    private EntityId contractorId;
    private EntityId workerId;
    private PaymentMethod paymentMethod;
    private double amount;
    private String reason;
    private final Clock creationClock;

    private PaymentBuilder(EntityId id, Clock creationClock)
    {
        this.id = id;
        this.creationClock = creationClock;
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
                Objects.requireNonNull(this.creationClock)
        );
    }

    public static PaymentBuilder init(EntityId id, Clock creationClock)
    {
        return new PaymentBuilder(id, creationClock);
    }

    public PaymentBuilder setContractorId(EntityId contractorId)
    {
        PaymentBuilder builder = this;
        builder.contractorId = contractorId;
        return builder;
    }

    public PaymentBuilder setWorkerId(EntityId workerId)
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
