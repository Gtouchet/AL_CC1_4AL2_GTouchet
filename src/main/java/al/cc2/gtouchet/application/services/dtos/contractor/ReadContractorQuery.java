package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Query;
import al.cc2.gtouchet.domain.valueObjects.Id;

public final class ReadContractorQuery implements Query
{
    public final Id id;

    public ReadContractorQuery(Id id)
    {
        this.id = id;
    }
}
