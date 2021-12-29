package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Query;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class ReadContractorQuery implements Query
{
    public final EntityId id;

    public ReadContractorQuery(EntityId id)
    {
        this.id = id;
    }
}
