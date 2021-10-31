package esgi.al.models;

import esgi.al.enumerators.PaymentMethod;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User
{
    private final UUID id;
    private final String login;
    private String password;
    private String name;
    private PaymentMethod paymentMethod;
    private Address address;

    private User(String login, String password, String name, PaymentMethod paymentMethod, Address address)
    {
        if (!this.verifyUserValidity(login, password))
        {
            throw new IllegalArgumentException();
        }

        this.id = java.util.UUID.randomUUID();
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

    public static User of(String login, String password, String name, PaymentMethod paymentMethod, Address address)
    {
        return new User(login, password, name, paymentMethod, address);
    }

    public static User of(User jsonUser)
    {
        return new User(jsonUser.login, jsonUser.password, jsonUser.name, jsonUser.paymentMethod, jsonUser.address);
    }

    /**
     * Getters
     */
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
    public Boolean setPassword(String password)
    {
        if (this.verifyPasswordValidity(password))
        {
            this.password = password;
            return true;
        }
        return false;
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

    /**
     * Properties validation
     */
    private Boolean verifyUserValidity(String login, String password)
    {
        return !login.equals("") && this.verifyPasswordValidity(password);
    }

    private Boolean verifyPasswordValidity(String password)
    {
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%,?;.:/!§]).{4,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
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
