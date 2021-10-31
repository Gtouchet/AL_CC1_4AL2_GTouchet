package esgi.al.models;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.utils.Validator;

import java.util.Objects;

public class User
{
    private final String id;
    private final String login;
    private String password;
    private String name;
    private PaymentMethod paymentMethod;
    private Address address;

    private User(String id, String login, String password, String name, PaymentMethod paymentMethod, Address address)
    {
        if (!Validator.isUserValid(login, password))
        {
            throw new IllegalArgumentException();
        }

        this.id = Objects.requireNonNullElse(id, java.util.UUID.randomUUID().toString());
        this.login = Objects.requireNonNull(login);
        this.password = Objects.requireNonNull(password);
        this.name = Objects.requireNonNullElse(name, "<Unspecified name>");
        this.paymentMethod = Objects.requireNonNullElse(paymentMethod, PaymentMethod.UNSPECIFIED);
        this.address = Address.of(
                address.getCity(),
                address.getStreetType(),
                address.getStreetName(),
                address.getStreetNumber()
        );
    }

    public static User of(String id, String login, String password, String name, PaymentMethod paymentMethod, Address address)
    {
        return new User(id, login, password, name, paymentMethod, address);
    }

    public static User of(User jsonUser)
    {
        return new User(jsonUser.id, jsonUser.login, jsonUser.password, jsonUser.name, jsonUser.paymentMethod, jsonUser.address);
    }

    /**
     * Getters
     */
    public String getId()
    {
        return this.id.toString();
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

    public PaymentMethod getPaymentMethod()
    {
        return this.paymentMethod;
    }

    public Address getAddress()
    {
        return this.address;
    }

    /**
     * Setters
     */
    public void setPassword(String password)
    {
        if (Validator.isPasswordValid(password))
        {
            this.password = password;
        }
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public void setAddress(Address address)
    {
        this.address = Address.of(
                address.getCity(),
                address.getStreetType(),
                address.getStreetName(),
                address.getStreetNumber()
        );
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
