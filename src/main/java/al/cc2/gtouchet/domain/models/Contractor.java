package al.cc2.gtouchet.domain.models;

import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.domain.valueObjects.Password;

public class Contractor extends User
{
    private final PaymentMethod paymentMethod;
    private final boolean isPaymentValidated;

    private Contractor(Id id, String login, Password password, String name,
                       PaymentMethod paymentMethod, boolean isPaymentValidated, Date creationDate)
    {
        super(id, login, password, name, creationDate);

        this.paymentMethod = paymentMethod;
        this.isPaymentValidated = isPaymentValidated;
    }

    public static Contractor of(
            Id id, String login, Password password, String name,
            PaymentMethod paymentMethod, boolean isPaymentValidated, Date creationDate)
    {
        return new Contractor(id, login, password, name, paymentMethod, isPaymentValidated, creationDate);
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
