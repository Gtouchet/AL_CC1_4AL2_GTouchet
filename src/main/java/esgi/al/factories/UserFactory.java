package esgi.al.factories;

import esgi.al.models.User;

public class UserFactory
{
    public static User create()
    {
        return User.init();
    }
}
