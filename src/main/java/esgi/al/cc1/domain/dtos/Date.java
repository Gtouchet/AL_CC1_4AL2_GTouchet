package esgi.al.cc1.domain.dtos;

import java.text.SimpleDateFormat;

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
    public String toString()
    {
        return this.value;
    }
}
