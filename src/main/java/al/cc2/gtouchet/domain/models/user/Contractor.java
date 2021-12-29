package al.cc2.gtouchet.domain.models.user;

import al.cc2.gtouchet.domain.models.payment.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class Contractor extends User
{
    private final PaymentMethod paymentMethod;
    private final boolean isPaymentValidated;

    private Contractor(Id id, Credentials credentials, String name,
                       PaymentMethod paymentMethod, boolean isPaymentValidated, Date creationDate)
    {
        super(id, credentials, name, creationDate);

        this.paymentMethod = paymentMethod;
        this.isPaymentValidated = isPaymentValidated;
    }

    public static Contractor of(
            Id id, Credentials credentials, String name,
            PaymentMethod paymentMethod, boolean isPaymentValidated, Date creationDate)
    {
        return new Contractor(id, credentials, name, paymentMethod, isPaymentValidated, creationDate);
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
