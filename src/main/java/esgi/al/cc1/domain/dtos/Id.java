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

    @Override
    public String toString()
    {
        return this.value;
    }
}
