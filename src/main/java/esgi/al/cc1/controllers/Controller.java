package esgi.al.controllers;

import esgi.al.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;

import java.util.stream.Stream;

public interface Controller<T>
{
    void create(T element) throws InvalidModelParameter, FailedToCreate;
    Stream<T> read();
    T read(String id) throws ElementNotFound;
    void update(T element) throws InvalidModelParameter, ElementNotFound, FailedToCreate;
    void remove(String id) throws ElementNotFound;
}
