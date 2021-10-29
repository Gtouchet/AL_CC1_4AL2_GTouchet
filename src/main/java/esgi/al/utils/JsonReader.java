package esgi.al.utils;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.enumerators.StreetType;
import esgi.al.models.Address;
import esgi.al.models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JsonReader
{
    public static List<User> getUserDataFrom(String path)
    {
        List<User> users = new ArrayList<>();
        try {
            JSONArray jsonUsers = (JSONArray) new JSONParser().parse(new FileReader(path));
            jsonUsers.forEach(jsonUser -> users.add(parseUserObject((JSONObject) jsonUser)));
            System.out.println(users);
            return users;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static User parseUserObject(JSONObject jsonUser)
    {
        return User.of(
                UUID.fromString((String) jsonUser.get("id")),
                (String) jsonUser.get("login"),
                (String) jsonUser.get("password"),
                (String) jsonUser.get("name"),
                // Todo: read data in data ?
                Address.of(
                        (String) jsonUser.get("address.city"),
                        StreetType.rue, //StreetType.valueOf((String) jsonUser.get("streetType")),
                        "", //(String) jsonUser.get("streetName"),
                        0 //(int) jsonUser.get("streetNumber")
                ),
                PaymentMethod.valueOf((String) jsonUser.get("paymentMethod"))
        );
    }
}
