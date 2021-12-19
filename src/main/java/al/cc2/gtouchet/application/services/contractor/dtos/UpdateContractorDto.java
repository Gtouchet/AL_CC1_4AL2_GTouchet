package al.cc2.gtouchet.application.services.contractor.dtos;

import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.domain.valueObjects.Password;

public class UpdateContractorDto
{
    public final Id id;
    public final Password password;
    public final String name;
    public final PaymentMethod paymentMethod;

    public UpdateContractorDto(
            Id id,
            Password password,
            String name,
            PaymentMethod paymentMethod)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.paymentMethod = paymentMethod;
    }
}
