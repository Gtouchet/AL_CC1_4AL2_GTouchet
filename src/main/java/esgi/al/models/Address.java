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

    @Override
    public String toString()
    {
        return this.streetNumber + " " + this.streetType + " " + this.streetName + ", " + this.city;
    }
}
