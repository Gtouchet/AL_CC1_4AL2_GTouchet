package esgi.al.cc1.validators;

import esgi.al.cc1.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.models.Address;

import java.util.Objects;

public class AddressValidator
{
    public static void validate(Address address) throws InvalidModelParameter
    {
        validateCity(address.getCity());
        validateStreet(address.getStreet());
        validateNumber(address.getNumber());
    }

    public static void validateCity(String city) throws InvalidModelParameter
    {
        Objects.requireNonNullElse(city, "");

        if (city.trim().equals(""))
        {
            throw new InvalidModelParameter("city", city);
        }
    }

    public static void validateStreet(String street) throws InvalidModelParameter
    {
        Objects.requireNonNullElse(street, "");

        if (street.trim().equals(""))
        {
            throw new InvalidModelParameter("street", street);
        }
    }

    public static void validateNumber(int number) throws InvalidModelParameter
    {
        if (number <= 0)
        {
            throw new InvalidModelParameter("number", Integer.toString(number));
        }
    }
}
