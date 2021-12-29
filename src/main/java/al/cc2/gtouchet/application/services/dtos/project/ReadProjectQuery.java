package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Query;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

public final class ReadProjectQuery implements Query
{
    public final EntityId id;

    public ReadProjectQuery(EntityId id)
    {
        this.id = id;
    }
}
