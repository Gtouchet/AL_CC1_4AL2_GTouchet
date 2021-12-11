package esgi.al.gtouchet.cc2.infrastructure.dataAccessors;

import java.util.List;

public interface DataAccessor<T>
{
    T[] getData();
    void write(List<T> data);
}
