package al.cc2.gtouchet.application.services.dtos.payment;

import al.cc2.gtouchet.application.kernel.Query;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class ReadPaymentQuery implements Query
{
    public final Id id;

    public ReadPaymentQuery(Id id)
    {
        this.id = id;
    }
}
