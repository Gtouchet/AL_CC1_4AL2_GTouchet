package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Password;

public final class CreateContractorCommand implements Command
{
    public final String login;
    public final Password password;
    public final String name;
    public final PaymentMethod paymentMethod;

    public CreateContractorCommand(
            String login,
            Password password,
            String name,
            PaymentMethod paymentMethod)
    {
        this.login = login;
        this.password = password;
        this.name = name;
        this.paymentMethod = paymentMethod;
    }
}