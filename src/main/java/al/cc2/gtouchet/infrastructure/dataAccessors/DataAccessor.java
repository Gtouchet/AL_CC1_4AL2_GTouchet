package al.cc2.gtouchet.infrastructure.dataAccessors;

import al.cc2.gtouchet.domain.models.Entity;

import java.util.List;

public interface DataAccessor<T extends Entity>
{
    List<T> getData();
    void write(List<T> data);
}
