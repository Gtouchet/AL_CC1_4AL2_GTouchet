package esgi.al.models;

import esgi.al.daos.AddressDao;
import esgi.al.daos.UserDao;
import esgi.al.exceptions.modelsExceptions.InvalidAddressParameter;
import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
import esgi.al.validators.AddressValidator;
import esgi.al.validators.UserValidator;

import java.util.Objects;
import java.util.UUID;

public class User extends UserDao
{
    private User(UserDao userDao)
    {
        this.id = Objects.requireNonNullElse(userDao.id, UUID.randomUUID().toString());
        this.login = userDao.login;
        this.password =  userDao.password;
        this.name = userDao.name;
        this.paymentMethod =  userDao.paymentMethod;
        this.address = Address.of(userDao.address);
    }

    public static User of(UserDao userDao)
    {
        return new User(userDao);
    }

    /**
     * Getters
     */
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

    public AddressDao getAddress()
    {
        return this.address;
    }

    /**
     * Setters
     */
    public void setPassword(String password) throws InvalidUserParameter
    {
        UserValidator.validatePassword(password);
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPaymentMethod(String paymentMethod) throws InvalidUserParameter
    {
        UserValidator.validatePaymentMethod(paymentMethod);
        this.paymentMethod = paymentMethod;
    }

    public void setAddress(Address address) throws InvalidAddressParameter
    {
        AddressValidator.validate(address);
        this.address = address;
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
