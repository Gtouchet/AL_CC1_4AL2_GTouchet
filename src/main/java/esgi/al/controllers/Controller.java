package esgi.al.controllers;

import esgi.al.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;

import java.util.stream.Stream;

public interface Controller<T>
{
    void post(T element) throws InvalidModelParameter, FailedToCreate;
    Stream<T> get();
    T get(String id) throws ElementNotFound;
    void put(T element) throws InvalidModelParameter, ElementNotFound, FailedToCreate;
    void del(String id) throws ElementNotFound;
}
