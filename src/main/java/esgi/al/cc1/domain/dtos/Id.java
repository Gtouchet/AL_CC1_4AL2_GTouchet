package esgi.al.cc1.domain.dtos;

import java.util.UUID;

public class Id
{
    private final String value;

    private Id(String value)
    {
        this.value = value;
    }

    public static Id generate()
    {
        return new Id(UUID.randomUUID().toString());
    }

    public static Id set(String value)
    {
        return new Id(value);
    }

    @Override
    public String toString()
    {
        return this.value;
    }
}
