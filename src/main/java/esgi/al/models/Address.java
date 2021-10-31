package esgi.al.models;

import esgi.al.enumerators.StreetType;
import esgi.al.utils.Validator;

import java.util.Objects;

public class Address
{
    private String city;
    private StreetType streetType;
    private String streetName;
    private int streetNumber;

    private Address(String city, StreetType streetType, String streetName, int streetNumber)
    {
        if (!Validator.isAddressValid(city, streetName, streetNumber))
        {
            throw new IllegalArgumentException();
        }

        this.city = Objects.requireNonNullElse(city, "<Unspecified city>");
        this.streetType = Objects.requireNonNullElse(streetType, StreetType.unspecified);
        this.streetName = Objects.requireNonNullElse(streetName, "<Unspecified street name>");
        this.streetNumber = Objects.requireNonNullElse(streetNumber, -1);
    }

    public static Address of(String city, StreetType streetType, String streetName, int streetNumber)
    {
        return new Address(city, streetType, streetName, streetNumber);
    }

    /**
     * Getters
     */
    public String getCity()
    {
        return this.city;
    }

    public StreetType getStreetType()
    {
        return this.streetType;
    }

    public String getStreetName()
    {
        return this.streetName;
    }

    public int getStreetNumber()
    {
        return this.streetNumber;
    }

    /**
     * Setters
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    public void setStreetType(StreetType streetType)
    {
        this.streetType = streetType;
    }

    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    public void setStreetNumber(int streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    @Override
    public String toString()
    {
        return this.streetNumber + " " + this.streetType + " " + this.streetName + ", " + this.city;
    }
}
