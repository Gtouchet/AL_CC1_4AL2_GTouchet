package al.cc2.gtouchet.application.services.contractor.dtos;

import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Password;

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