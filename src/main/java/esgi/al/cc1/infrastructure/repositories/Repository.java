package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Entity;
import esgi.al.cc1.domain.valueObjects.Id;

import java.util.stream.Stream;

public interface Repository<T extends Entity>
{
    void create(T entity) throws FailedToCreateException;
    Stream<T> read();
    T read(Id id) throws ElementNotFoundException;
    void update(Id id, T entity) throws ElementNotFoundException, FailedToUpdateException;
    void remove(Id id) throws ElementNotFoundException;

    boolean exists(Id id);
}
