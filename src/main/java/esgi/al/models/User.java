package esgi.al.models;

import esgi.al.enums.PaymentMethod;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class User
{
    private String login;
    private String password;
    private String name;
    private Address address;
    private PaymentMethod paymentMethod;

    private User()
    {
        // Todo: implements
        throw new NotImplementedException();
    }

    public static User init()
    {
        return new User();
    }
}
