package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Command;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class ValidatePaymentCommand implements Command
{
    public final Id id;

    public ValidatePaymentCommand(Id id)
    {
        this.id = id;
    }
}
