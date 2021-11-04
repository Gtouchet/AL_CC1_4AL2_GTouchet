package esgi.al.validators;

import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
import esgi.al.models.User;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator
{
    public static void validate(User user) throws InvalidUserParameter
    {
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validatePaymentMethod(user.getPaymentMethod());
    }

    public static void validateLogin(String login) throws InvalidUserParameter
    {
        Objects.requireNonNullElse(login, "");

        if (login.trim().equals(""))
        {
            throw new InvalidUserParameter("login", login);
        }
    }

    public static void validatePassword(String password) throws InvalidUserParameter
    {
        Objects.requireNonNullElse(password, "");
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%,?;.:/!§]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        if (password.trim().equals("") || !matcher.matches())
        {
            throw new InvalidUserParameter("password", password);
        }
    }

    public static void validatePaymentMethod(String paymentMethodStr) throws InvalidUserParameter
    {
        Objects.requireNonNullElse(paymentMethodStr, "");

        if (!paymentMethodStr.equals("card") && !paymentMethodStr.equals("paypal"))
        {
            throw new InvalidUserParameter("payment method", paymentMethodStr);
        }
    }
}
