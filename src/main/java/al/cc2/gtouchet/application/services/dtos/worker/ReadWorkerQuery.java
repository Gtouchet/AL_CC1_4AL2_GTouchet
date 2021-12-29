package al.cc2.gtouchet.application.services.dtos.worker;

import al.cc2.gtouchet.application.kernel.Query;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class ReadWorkerQuery implements Query
{
    public final EntityId id;

    public ReadWorkerQuery(EntityId id)
    {
        this.id = id;
    }
}
