package esgi.al.console.enumerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CommandKeyword
{
    CREATE("CREATE USER login password name paymentMethod number street city\n" +
            "CREATE PAYMENT userId amount reason"),
    GET("GET USER/PAYMENT\n" +
            "GET USER/PAYMENT id"),
    UPDATE("UPDATE USER id login password name paymentMethod number street city"),
    DELETE("DELETE USER/PAYMENT id"),
    QUIT("QUIT");

    public final String usageExample;

    CommandKeyword(String usageExample)
    {
        this.usageExample = usageExample;
    }

    public static List<CommandKeyword> getCommandKeywords()
    {
        return new ArrayList<>(Arrays.asList(CommandKeyword.values()));
    }

    public static CommandKeyword stringToCommand(String value)
    {
        for (CommandKeyword keyword : CommandKeyword.values())
        {
            if (keyword.toString().equals(value))
            {
                return keyword;
            }
        }
        return null;
    }
}
