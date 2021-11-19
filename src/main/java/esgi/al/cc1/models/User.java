package esgi.al.cc1.models;

import java.util.UUID;

public class User
{
    private final String id;
    private final String login;
    private final String password;
    private final String name;
    private final String paymentMethod;
    private final Address address;

    private User(
            String id,
            String login,
            String password,
            String name,
            String paymentMethod,
            Address address
    ) {
        this.id = id;
        this.login = login;
        this.password =  password;
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.address = Address.of(address);
    }

    public static User of(
            String id,
            String login,
            String password,
            String name,
            String paymentMethod,
            Address address
    ) {
        return new User(
                id,
                login,
                password,
                name,
                paymentMethod,
                address
        );
    }

    public static User of(
            String login,
            String password,
            String name,
            String paymentMethod,
            Address address
    ) {
        return new User(
                UUID.randomUUID().toString(),
                login,
                password,
                name,
                paymentMethod,
                address
        );
    }

    public String getId()
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

    public String getPaymentMethod()
    {
        return this.paymentMethod;
    }

    public Address getAddress()
    {
        return this.address;
    }

    @Override
    public String toString()
    {
        return "ID: " + this.id +
                "\nLogin: " + this.login +
                "\nPassword: " + this.password +
                "\nName: " + this.name +
                "\nPayment method: " + this.paymentMethod +
                "\nAddress: " + this.address.toString();
    }
}
