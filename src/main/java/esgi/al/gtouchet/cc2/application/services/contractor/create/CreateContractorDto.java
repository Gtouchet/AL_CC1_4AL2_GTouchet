package esgi.al.gtouchet.cc2.application.services.contractor.create;

import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

public class CreateContractorDto
{
    public final String login;
    public final Password password;
    public final String name;
    public final PaymentMethod paymentMethod;

    public CreateContractorDto(
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