package esgi.al.validators;

import esgi.al.daos.AddressDao;
import esgi.al.exceptions.modelsExceptions.InvalidAddressParameter;

import java.util.Objects;

public class AddressValidator
{
    public static void validate(AddressDao address) throws InvalidAddressParameter
    {
        validateCity(address.city);
        validateStreet(address.street);
        validateNumber(address.number);
    }

    public static void validateCity(String city) throws InvalidAddressParameter
    {
        Objects.requireNonNullElse(city, "");
        if (city.trim().equals(""))
        {
            throw new InvalidAddressParameter("city", city);
        }
    }

    public static void validateStreet(String street) throws InvalidAddressParameter
    {
        Objects.requireNonNullElse(street, "");
        if (street.trim().equals(""))
        {
            throw new InvalidAddressParameter("street", street);
        }
    }

    public static void validateNumber(int number) throws InvalidAddressParameter
    {
        if (number <= 0)
        {
            throw new InvalidAddressParameter("number", Integer.toString(number));
        }
    }
}
