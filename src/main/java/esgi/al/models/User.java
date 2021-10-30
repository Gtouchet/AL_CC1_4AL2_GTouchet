package esgi.al.models;

import esgi.al.enumerators.PaymentMethod;
import java.util.UUID;

public class User
{
    private final UUID id;
    private final String login;
    private final String password;
    private final String name;
    private final Address address;
    private final PaymentMethod paymentMethod;

    private User(UUID id, String login, String password, String name, Address address, PaymentMethod paymentMethod)
    {
        // Todo: implements properties verifications here
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.address = address;
        this.paymentMethod = paymentMethod;
    }

    public static User of(UUID id, String login, String password, String name, Address address, PaymentMethod paymentMethod)
    {
        return new User(id, login, password, name, address, paymentMethod);
    }

    public UUID getId()
    {
        return this.id;
    }
    public String getLogin()
    {
        return this.login;
    }
    public String getPassword()
    {
        return this.password;
    }
    public String getName()
    {
        return this.name;
    }
    public Address getAddress()
    {
        return this.address;
    }
    public PaymentMethod getPaymentMethod()
    {
        return this.paymentMethod;
    }

    @Override
    public String toString()
    {
        return "ID: " + this.id +
                "\nLogin: " + this.login +
                "\nPassword: " + this.password +
                "\nName: " + this.name +
                "\nAddress: " + this.address +
                "\nPayment method: " + this.paymentMethod;
    }
}
