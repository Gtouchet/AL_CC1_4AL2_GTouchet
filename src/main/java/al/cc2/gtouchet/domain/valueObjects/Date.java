package al.cc2.gtouchet.domain.valueObjects;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class Date
{
    private final String value;

    private Date()
    {
        this.value = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new java.util.Date());
    }

    public static Date now()
    {
        return new Date();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return Objects.equals(value, date.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(value);
    }

    @Override
    public String toString()
    {
        return this.value;
    }
}
