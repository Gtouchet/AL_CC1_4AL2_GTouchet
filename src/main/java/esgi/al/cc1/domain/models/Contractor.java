package esgi.al.cc1.domain.models;

import java.util.UUID;

public class Contractor
{
    private final String id;
    private final String login;
    private final String password;
    private final String name;
    private final String paymentMethod;

    private Contractor(
            String id,
            String login,
            String password,
            String name,
            String paymentMethod
    ) {
        this.id = id;
        this.login = login;
        this.password =  password;
        this.name = name;
        this.paymentMethod = paymentMethod;
    }

    public static Contractor of(
            String id,
            String login,
            String password,
            String name,
            String paymentMethod
    ) {
        return new Contractor(
                id,
                login,
                password,
                name,
                paymentMethod
        );
    }

    public static Contractor of(
            String login,
            String password,
            String name,
            String paymentMethod
    ) {
        return new Contractor(
                UUID.randomUUID().toString(),
                login,
                password,
                name,
                paymentMethod
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

    @Override
    public String toString()
    {
        return "ID: " + this.id +
                "\nLogin: " + this.login +
                "\nPassword: " + this.password +
                "\nName: " + this.name +
                "\nPayment method: " + this.paymentMethod;
    }
}
