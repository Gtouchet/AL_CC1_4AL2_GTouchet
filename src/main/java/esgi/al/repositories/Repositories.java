package esgi.al.repositories;

import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;

import java.util.stream.Stream;

public interface Repositories<T>
{
    void post(T element) throws FailedToCreate;
    Stream<T> get() throws ElementNotFound;
    T get(String id) throws ElementNotFound;
    void put(String id, T element) throws ElementNotFound, FailedToCreate;
    void del(String id) throws ElementNotFound;
}
