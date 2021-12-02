package esgi.al.cc1.domain.models;

import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

public class Contractor extends Entity
{
    private final String login;
    private Password password;
    private String name;
    private PaymentMethod paymentMethod;
    private boolean isPaymentValidated;

    private Contractor(Id id, String login, Password password, String name,
                       PaymentMethod paymentMethod, boolean isPaymentValidated, Date creationDate)
    {
        super(id, creationDate);

        this.login = login;
        this.password = password;
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.isPaymentValidated = isPaymentValidated;
    }

    public static Contractor of(
            Id id, String login, Password password, String name,
            PaymentMethod paymentMethod, boolean isPaymentValidated, Date creationDate)
    {
        return new Contractor(id, login, password, name, paymentMethod, isPaymentValidated, creationDate);
    }

    public String getLogin()
    {
        return this.login;
    }

    public Password getPassword()
    {
        return this.password;
    }

    public String getName()
    {
        return this.name;
    }

    public PaymentMethod getPaymentMethod()
    {
        return this.paymentMethod;
    }

    public boolean isPaymentValidated()
    {
        return this.isPaymentValidated;
    }

    public void setPassword(Password password)
    {
        this.password = Password.of(password);
        this.setUpdateDate();
    }

    public void setName(String name)
    {
        this.name = name;
        this.setUpdateDate();
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
                "\nLogin: " + this.login +
                "\nPassword: " + this.password +
                "\nName: " + this.name +
                "\nPayment method: " + this.paymentMethod +
                "\nIs payment validated: " + this.isPaymentValidated;
    }
}
