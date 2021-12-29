package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.models.payment.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.EntityId;
import al.cc2.gtouchet.domain.valueObjects.Password;

public final class UpdateContractorCommand implements Command
{
    public final EntityId id;
    public final Password password;
    public final String name;
    public final PaymentMethod paymentMethod;

    public UpdateContractorCommand(
            EntityId id,
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
