package esgi.al.cliInterpreter;

public enum Table
{
    USER,
    PAYMENT;

    public static Table getFromString(String value)
    {
        for (Table table : Table.values())
        {
            if (table.toString().equals(value))
            {
                return table;
            }
        }
        return null;
    }
}
