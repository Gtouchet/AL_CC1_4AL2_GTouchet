package al.cc2.gtouchet.domain.valueObjects;

import java.util.Objects;
import java.util.UUID;

public final class EntityId
{
    private final String value;

    private EntityId(String id)
    {
        this.value = id;
    }

    public static EntityId generate()
    {
        return new EntityId(UUID.randomUUID().toString());
    }

    public static EntityId fromString(String value)
    {
        return new EntityId(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityId id = (EntityId) o;
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
