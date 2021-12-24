package al.cc2.gtouchet.application.services.dtos.project;

import al.cc2.gtouchet.application.kernel.Query;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class ReadProjectQuery implements Query
{
    public final Id id;

    public ReadProjectQuery(Id id)
    {
        this.id = id;
    }
}
