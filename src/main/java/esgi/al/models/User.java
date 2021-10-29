package esgi.al.models;

import esgi.al.enumerators.PaymentMethod;

public class User
{
    private final String login;
    private String password;
    private final String name;
    private final Address address;
    private final PaymentMethod paymentMethod;

    private User(String login, String password, String name, Address address, PaymentMethod paymentMethod)
    {
        this.login = login;
        this.password = password;
        this.name = name;
        this.address = address;
        this.paymentMethod = paymentMethod;
    }

    public static User of(String login, String password, String name, Address address, PaymentMethod paymentMethod)
    {
        return new User(login, password, name, address, paymentMethod);
    }

    public void updatePassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Login: " + this.login +
                "\nPassword: " + this.password +
                "\nName: " + this.name +
                "\nAddress: " + this.address +
                "\nPayment method: " + this.paymentMethod;
    }
}
