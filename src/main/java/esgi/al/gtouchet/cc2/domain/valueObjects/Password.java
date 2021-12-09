package esgi.al.gtouchet.cc2.domain.valueObjects;

import java.util.Objects;

public class Password
{
    private final String value;

    private Password(String value)
    {
        this.value = value;
    }

    public static Password of(String value)
    {
        return new Password(value);
    }

    public static Password of(Password value)
    {
        return new Password(value.toString());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
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
