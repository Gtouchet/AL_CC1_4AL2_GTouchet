package esgi.al.models;

import esgi.al.daos.AddressDao;
import esgi.al.exceptions.modelsExceptions.InvalidAddressParameter;
import esgi.al.validators.AddressValidator;

public class Address extends AddressDao
{
    private Address(AddressDao addressDao)
    {
        this.city = addressDao.city;
        this.street = addressDao.street;
        this.number = addressDao.number;
    }

    public static Address of(AddressDao addressDao)
    {
        return new Address(addressDao);
    }

    /**
     * Getters
     */
    public String getCity()
    {
        return this.city;
    }

    public String getStreet()
    {
        return this.street;
    }

    public int getNumber()
    {
        return this.number;
    }

    /**
     * Setters
     */
    public void setCity(String city) throws InvalidAddressParameter
    {
        AddressValidator.validateCity(city);
        this.city = city;
    }

    public void setStreet(String street) throws InvalidAddressParameter
    {
        AddressValidator.validateStreet(street);
        this.street = street;
    }

    public void setNumber(int number) throws InvalidAddressParameter
    {
        AddressValidator.validateNumber(number);
        this.number = number;
    }

    @Override
    public String toString()
    {
        return this.number + " " + this.street + ", " + this.city;
    }
}
