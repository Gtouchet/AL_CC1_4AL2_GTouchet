package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.models.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.domain.valueObjects.Password;

public final class UpdateContractorCommand implements Command
{
    public final Id id;
    public final Password password;
    public final String name;
    public final PaymentMethod paymentMethod;

    public UpdateContractorCommand(
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
