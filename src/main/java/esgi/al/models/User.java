package esgi.al.models;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.utils.Uuid;

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
    private Address address;
    private PaymentMethod paymentMethod;

    private User(User user)
    {
        if (!this.verifyUserValidity(user))
        {
            throw new IllegalArgumentException();
        }

        this.id = Objects.requireNonNullElse(user.id, Uuid.generate());
        this.login = Objects.requireNonNull(user.login);
        this.password = Objects.requireNonNull(user.password);
        this.name = Objects.requireNonNullElse(user.name, "<Unspecified name>");
        this.address = Address.of(user.address);
        this.paymentMethod = Objects.requireNonNullElse(user.paymentMethod, PaymentMethod.UNSPECIFIED);
    }

    public static User of(User user)
    {
        return new User(user);
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

    public Boolean setPassword(String password)
    {
        if (this.verifyPasswordValidity(password))
        {
            this.password = password;
            return true;
        }
        return false;
    }

    public void setName()
    {
        this.name = name;
    }

    public void setAddress(Address address)
    {
        this.address = Address.of(address);
    }

    public void setPaymentMethod(PaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    private Boolean verifyUserValidity(User user)
    {
        return !user.login.equals("") && this.verifyPasswordValidity(user.password);
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
