package esgi.al.cc1.domain.dtos;

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

    public Password set(String password)
    {
        return new Password(password);
    }

    @Override
    public String toString()
    {
        return this.value;
    }
}
