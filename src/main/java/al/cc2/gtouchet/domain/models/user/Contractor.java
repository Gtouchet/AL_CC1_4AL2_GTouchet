package al.cc2.gtouchet.domain.models.user;

import al.cc2.gtouchet.domain.models.payment.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class Contractor extends User
{
    private final PaymentMethod paymentMethod;
    private final boolean isPaymentValidated;

    private Contractor(EntityId id, Credentials credentials, String name,
                       PaymentMethod paymentMethod, boolean isPaymentValidated, Clock creationClock)
    {
        super(id, credentials, name, creationClock);

        this.paymentMethod = paymentMethod;
        this.isPaymentValidated = isPaymentValidated;
    }

    public static Contractor of(
            EntityId id, Credentials credentials, String name,
            PaymentMethod paymentMethod, boolean isPaymentValidated, Clock creationClock)
    {
        return new Contractor(id, credentials, name, paymentMethod, isPaymentValidated, creationClock);
    }

    public PaymentMethod getPaymentMethod()
    {
        return this.paymentMethod;
    }

    public boolean isPaymentValidated()
    {
        return this.isPaymentValidated;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "\nPayment method: " + this.paymentMethod +
                "\nIs payment validated: " + this.isPaymentValidated;
    }
}
