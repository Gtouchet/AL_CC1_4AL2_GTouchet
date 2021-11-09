package esgi.al.console.enumerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TableName
{
    USER,
    PAYMENT;

    public static List<TableName> getTableNames()
    {
        return new ArrayList<>(Arrays.asList(TableName.values()));
    }

    public static TableName stringToTable(String value)
    {
        for (TableName table : TableName.values())
        {
            if (table.toString().equals(value))
            {
                return table;
            }
        }
        return null;
    }
}
