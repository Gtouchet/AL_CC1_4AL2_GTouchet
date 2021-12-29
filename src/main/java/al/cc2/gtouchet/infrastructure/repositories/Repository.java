package al.cc2.gtouchet.infrastructure.repositories;

import al.cc2.gtouchet.domain.models.Entity;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

import java.util.stream.Stream;

public interface Repository<T extends Entity>
{
    void create(T entity);
    Stream<T> read();
    T read(EntityId id) throws EntityNotFoundException;
    void update(EntityId id, T entity) throws EntityNotFoundException;
    void remove(EntityId id) throws EntityNotFoundException;

    boolean exists(EntityId id);
}
