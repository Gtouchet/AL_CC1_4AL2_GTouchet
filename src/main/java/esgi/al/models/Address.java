package esgi.al.models;

import esgi.al.enumerators.StreetType;

import java.util.Objects;

public class Address
{
    private final String city;
    private final StreetType streetType;
    private final String streetName;
    private final int streetNumber;

    private Address(String city, StreetType streetType, String streetName, int streetNumber)
    {
        if (!this.verifyAddressValidity(city, streetName, streetNumber))
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
    // Todo: address setters

    /**
     * Properties validation
     */
    private Boolean verifyAddressValidity(String city, String streetName, int streetNumber)
    {
        return (city == null || !city.equals("")) &&
                (streetName == null || !streetName.equals("")) &&
                streetNumber > 0;
    }

    @Override
    public String toString()
    {
        return this.streetNumber + " " + this.streetType + " " + this.streetName + ", " + this.city;
    }
}
