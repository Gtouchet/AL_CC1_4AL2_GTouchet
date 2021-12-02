package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

public class Contractor extends User
{
    private PaymentMethod paymentMethod;
    private boolean isPaymentValidated;

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

    public void setPaymentMethod(PaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
        this.setUpdateDate();
    }

    public void setPaymentValidated(boolean isPaymentValidated)
    {
        this.isPaymentValidated = isPaymentValidated;
        this.setUpdateDate();
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "\nPayment method: " + this.paymentMethod +
                "\nIs payment validated: " + this.isPaymentValidated;
    }
}
