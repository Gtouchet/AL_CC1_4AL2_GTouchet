package esgi.al.repositories;

import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;

import java.util.stream.Stream;

public interface Repository<T>
{
    void post(T element) throws FailedToCreate;
    Stream<T> get();
    T get(String id) throws ElementNotFound;
    void put(T element) throws ElementNotFound, FailedToCreate;
    void del(String id) throws ElementNotFound;
}
