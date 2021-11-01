package esgi.al.daos;

public class AddressDao
{
    public String city;
    public String street;
    public int number;

    public AddressDao(int number, String street, String city)
    {
        this.number = number;
        this.street = street;
        this.city = city;
    }

    public AddressDao() { }
}
