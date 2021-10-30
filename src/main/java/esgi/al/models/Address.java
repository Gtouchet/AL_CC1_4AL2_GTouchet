package esgi.al.models;

import esgi.al.enumerators.StreetType;

public class Address
{
    private final String city;
    private final StreetType streetType;
    private final String streetName;
    private final int streetNumber;

    private Address(String city, StreetType streetType, String streetName, int streetNumber)
    {
        this.city = city;
        this.streetType = streetType;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }

    public static Address of(String city, StreetType streetType, String streetName, int streetNumber)
    {
        return new Address(city, streetType, streetName, streetNumber);
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

    @Override
    public String toString()
    {
        return this.streetNumber + " " + this.streetType + " " + this.streetName + ", " + this.city;
    }
}
