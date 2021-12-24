package al.cc2.gtouchet.application.services.dtos.worker;

import al.cc2.gtouchet.application.kernel.Query;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class ReadWorkerQuery implements Query
{
    public final Id id;

    public ReadWorkerQuery(Id id)
    {
        this.id = id;
    }
}
