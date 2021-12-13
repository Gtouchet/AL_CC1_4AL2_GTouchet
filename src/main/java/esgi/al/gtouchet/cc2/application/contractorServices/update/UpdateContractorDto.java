package esgi.al.gtouchet.cc2.application.contractorServices.update;

import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

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
