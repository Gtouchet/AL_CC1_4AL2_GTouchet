package al.cc2.gtouchet.infrastructure.dataAccessors;

import java.util.List;

public interface DataAccessor<T>
{
    List<T> getData();
    void write(List<T> data);
}
