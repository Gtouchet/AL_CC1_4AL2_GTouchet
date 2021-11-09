package esgi.al.repositories;

import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;

import java.util.stream.Stream;

public interface Repository<T>
{
    void create(T element) throws FailedToCreate;
    Stream<T> read();
    T read(String id) throws ElementNotFound;
    void update(T element) throws ElementNotFound, FailedToCreate;
    void remove(String id) throws ElementNotFound;
}
