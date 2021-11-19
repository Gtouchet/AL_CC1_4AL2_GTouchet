package esgi.al.cc1.models;

public class Address
{
    private final int number;
    private final String street;
    private final String city;

    private Address(
            int number,
            String street,
            String city
    ) {
        this.number = number;
        this.street = street;
        this.city = city;
    }

    public static Address of(Address address)
    {
        return new Address(
                address.getNumber(),
                address.getStreet(),
                address.getCity()
        );
    }

    public static Address of(
            int number,
            String street,
            String city
    ) {
        return new Address(
                number,
                street,
                city
        );
    }

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

    @Override
    public String toString()
    {
        return this.number + " " + this.street + ", " + this.city;
    }
}
