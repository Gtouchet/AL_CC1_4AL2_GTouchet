package esgi.al.gtouchet.cc2.domain.valueObjects;

import java.util.Objects;
import java.util.UUID;

public class Id
{
    private final String value;

    private Id(String id)
    {
        this.value = id;
    }

    public static Id generate()
    {
        return new Id(UUID.randomUUID().toString());
    }

    public static Id fromString(String value)
    {
        return new Id(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return Objects.equals(value, id.value);
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
