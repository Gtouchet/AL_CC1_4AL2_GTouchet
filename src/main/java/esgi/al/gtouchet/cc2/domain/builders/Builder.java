package esgi.al.gtouchet.cc2.domain.builders;

import esgi.al.gtouchet.cc2.domain.models.Entity;

public interface Builder<T extends Entity>
{
    T build();
}
