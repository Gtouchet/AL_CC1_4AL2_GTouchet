package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.dtos.Date;
import esgi.al.cc1.domain.dtos.Id;
import esgi.al.cc1.domain.dtos.Password;
import esgi.al.cc1.domain.enumerators.PaymentMethod;

public class Contractor extends User
{
    private PaymentMethod paymentMethod;
    private boolean isPaymentValidated;

    private Contractor(Id id, String login, Password password, String name, PaymentMethod paymentMethod, Date creationDate)
    {
        super(id, login, password, name, creationDate);

        this.paymentMethod = paymentMethod;
        this.isPaymentValidated = false;
    }

    public static Contractor of(Id id, String login, Password password, String name, PaymentMethod paymentMethod, Date creationDate)
    {
        return new Contractor(id, login, password, name, paymentMethod, creationDate);
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
