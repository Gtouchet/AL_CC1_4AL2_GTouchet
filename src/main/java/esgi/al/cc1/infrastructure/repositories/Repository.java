package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;

import java.util.stream.Stream;

public interface Repository<T>
{
    void create(T element) throws FailedToCreate;
    Stream<T> read();
    T read(String id) throws ElementNotFound;
    void update(T element) throws ElementNotFound, FailedToUpdate;
    void remove(String id) throws ElementNotFound;
}
