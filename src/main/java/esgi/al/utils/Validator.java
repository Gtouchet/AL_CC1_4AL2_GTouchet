package esgi.al.utils;

import esgi.al.enumerators.PaymentMethod;
import esgi.al.enumerators.StreetType;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator
{
    public static Boolean isUserValid(String login, String password)
    {
        return !login.equals("") && Validator.isPasswordValid(password);
    }

    public static Boolean isPasswordValid(String password)
    {
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%,?;.:/!§]).{4,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static Boolean isAddressValid(String city, String streetName, int streetNumber)
    {
        return (city == null || !city.equals("")) &&
                (streetName == null || !streetName.equals("")) &&
                streetNumber > 0;
    }

    public static Boolean isPaymentMethodValid(String strValue)
    {
        for (PaymentMethod paymentMethod : PaymentMethod.values())
        {
            if (paymentMethod.name().equals(strValue))
            {
                return true;
            }
        }
        return false;
    }

    public static Boolean isStreetTypeValid(String strValue)
    {
        for (StreetType streetType : StreetType.values())
        {
            if (streetType.name().equals(strValue))
            {
                return true;
            }
        }
        return false;
    }

    public static Boolean isUuidValid(String strValue)
    {
        try {
            UUID uuid = UUID.fromString(strValue);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
