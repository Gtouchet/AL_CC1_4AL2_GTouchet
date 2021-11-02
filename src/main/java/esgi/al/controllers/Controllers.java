package esgi.al.controllers;

import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;

import java.util.stream.Stream;

public interface Controllers<T, U>
{
    void post(U element) throws FailedToCreate;
    Stream<T> get() throws ElementNotFound;
    T get(String id) throws ElementNotFound;
    void put(String id, U element) throws ElementNotFound;
    void del(String id) throws ElementNotFound;
}
