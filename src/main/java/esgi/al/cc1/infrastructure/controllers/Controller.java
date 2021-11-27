package esgi.al.cc1.infrastructure.controllers;

import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;

import java.util.stream.Stream;

public interface Controller<T>
{
    void create(String[] values) throws FailedToCreate;
    Stream<T> read();
    T read(String id) throws ElementNotFound;
    void update(String[] values) throws ElementNotFound, FailedToUpdate;
    void remove(String id) throws ElementNotFound;
}
