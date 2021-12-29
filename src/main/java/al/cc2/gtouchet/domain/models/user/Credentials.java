package al.cc2.gtouchet.domain.models.user;

import al.cc2.gtouchet.domain.valueObjects.Password;

public final class Credentials
{
    private final String login;
    private final Password password;

    public Credentials(String login, Password password)
    {
        this.login = login;
        this.password = password;
    }

    public String getLogin()
    {
        return this.login;
    }

    public Password getPassword()
    {
        return this.password;
    }

    @Override
    public String toString()
    {
        return "\nLogin: " + this.login +
                "\nPassword: " + this.password;
    }
}
