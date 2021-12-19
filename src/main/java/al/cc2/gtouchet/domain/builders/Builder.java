package al.cc2.gtouchet.domain.builders;

import al.cc2.gtouchet.domain.models.Entity;

public interface Builder<T extends Entity>
{
    T build();
}
