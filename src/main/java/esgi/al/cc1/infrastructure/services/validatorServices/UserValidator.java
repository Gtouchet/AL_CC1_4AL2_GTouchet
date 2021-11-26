package esgi.al.cc1.infrastructure.services.validatorServices;

import esgi.al.cc1.infrastructure.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.domain.models.Contractor;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator
{
    public static void validate(Contractor contractor) throws InvalidModelParameter
    {
        validateLogin("contractor.getLogin()");
        validatePassword("contractor.getPassword()");
        validatePaymentMethod("contractor.getPaymentMethod()");
    }

    public static void validateLogin(String login) throws InvalidModelParameter
    {
        Objects.requireNonNullElse(login, "");

        if (login.trim().equals(""))
        {
            throw new InvalidModelParameter("login", login);
        }
    }

    public static void validatePassword(String password) throws InvalidModelParameter
    {
        Objects.requireNonNullElse(password, "");
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%,?;.:/!§]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        if (password.trim().equals("") || !matcher.matches())
        {
            throw new InvalidModelParameter("password", password);
        }
    }

    public static void validatePaymentMethod(String paymentMethodStr) throws InvalidModelParameter
    {
        Objects.requireNonNullElse(paymentMethodStr, "");

        if (!paymentMethodStr.equals("card") && !paymentMethodStr.equals("paypal"))
        {
            throw new InvalidModelParameter("payment method", paymentMethodStr);
        }
    }
}
