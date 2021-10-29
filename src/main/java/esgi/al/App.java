package esgi.al;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.enumerators.StreetType;
import esgi.al.models.Address;
import esgi.al.models.User;
import esgi.al.models.Users;
import esgi.al.repositories.SQLUsers;

public class App
{
    public static void main(String[] args)
    {
        final Users users = new SQLUsers();

        User user = User.of(
                "GTouchet",
                "Abcd1234!",
                "Touchet",
                Address.of(
                        "Draveil",
                        StreetType.rue,
                        "Pierre Brossolette",
                        134
                ),
                PaymentMethod.CARD
        );
    }
}
