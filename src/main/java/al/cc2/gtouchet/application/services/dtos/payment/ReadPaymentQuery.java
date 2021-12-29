package al.cc2.gtouchet.application.services.dtos.payment;

import al.cc2.gtouchet.application.kernel.Query;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class ReadPaymentQuery implements Query
{
    public final EntityId id;

    public ReadPaymentQuery(EntityId id)
    {
        this.id = id;
    }
}
