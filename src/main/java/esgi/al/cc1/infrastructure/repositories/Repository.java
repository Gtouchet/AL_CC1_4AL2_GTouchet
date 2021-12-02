package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Entity;
import esgi.al.cc1.domain.valueObjects.Id;

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
