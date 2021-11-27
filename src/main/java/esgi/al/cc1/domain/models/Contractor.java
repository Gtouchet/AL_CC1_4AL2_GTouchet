package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.dtos.Password;
import esgi.al.cc1.domain.enumerators.PaymentMethod;

public class Contractor extends User
{
    private PaymentMethod paymentMethod;
    private boolean isPaymentValidated;

    private Contractor(String login, Password password, String name, PaymentMethod paymentMethod)
    {
        super(login, password, name);

        this.paymentMethod = paymentMethod;
        this.isPaymentValidated = false;
    }

    public static Contractor of(String login, Password password, String name, PaymentMethod paymentMethod)
    {
        return new Contractor(login, password, name, paymentMethod);
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
    }

    public void setPaymentValidated(boolean isPaymentValidated)
    {
        this.isPaymentValidated = isPaymentValidated;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "\nPayment method: " + this.paymentMethod +
                "\nIs payment validated: " + this.isPaymentValidated;
    }
}
