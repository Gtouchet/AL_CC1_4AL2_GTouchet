package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Entity;
import esgi.al.cc1.domain.valueObjects.Id;

import java.util.stream.Stream;

public interface Repository<T extends Entity>
{
    void create(T element) throws FailedToCreateException;
    Stream<T> read();
    T read(Id id) throws ElementNotFoundException;
    void update(Id id, T element) throws ElementNotFoundException, FailedToUpdateException;
    void remove(Id id) throws ElementNotFoundException;

    boolean exists(Id id);

    void writeJsonFile();
}
