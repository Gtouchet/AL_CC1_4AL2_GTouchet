package al.cc2.gtouchet.infrastructure.repositories;

import al.cc2.gtouchet.domain.models.Entity;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.stream.Stream;

public interface Repository<T extends Entity>
{
    void create(T entity);
    Stream<T> read();
    T read(Id id) throws EntityNotFoundException;
    void update(Id id, T entity) throws EntityNotFoundException;
    void remove(Id id) throws EntityNotFoundException;

    boolean exists(Id id);
}
