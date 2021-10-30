package esgi.al.models;

import esgi.al.enumerators.StreetType;

import java.util.Objects;

public class Address
{
    private final String city;
    private final StreetType streetType;
    private final String streetName;
    private final int streetNumber;

    private Address(Address address)
    {
        if (!this.verifyAddressValidity(address))
        {
            throw new IllegalArgumentException();
        }

        this.city = Objects.requireNonNullElse(address.city, "<Unspecified city>");
        this.streetType = Objects.requireNonNullElse(address.streetType, StreetType.unspecified);
        this.streetName = Objects.requireNonNullElse(address.streetName, "<Unspecified street name>");
        this.streetNumber = Objects.requireNonNullElse(address.streetNumber, -1);
    }

    public static Address of(Address address)
    {
        return new Address(address);
    }

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

    private Boolean verifyAddressValidity(Address address)
    {
        return (address.city == null || !address.city.equals("")) &&
                (address.streetName == null || !address.streetName.equals("")) &&
                address.streetNumber > 0;
    }

    @Override
    public String toString()
    {
        return this.streetNumber + " " + this.streetType + " " + this.streetName + ", " + this.city;
    }
}
