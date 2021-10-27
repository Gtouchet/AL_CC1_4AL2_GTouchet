package esgi.al;

import esgi.al.models.User;
import esgi.al.factories.UserFactory;

public class App
{
    public static void main(String[] args)
    {
        User user = UserFactory.create();
    }
}
