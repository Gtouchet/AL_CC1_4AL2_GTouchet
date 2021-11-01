package esgi.al.daos;

public class UserDao
{
    public String id;
    public String login;
    public String password;
    public String name;
    public String paymentMethod;
    public AddressDao address;

    public UserDao(String id, String login, String password, String name, String paymentMethod, AddressDao address)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.address = address;
    }

    public UserDao() { }
}
