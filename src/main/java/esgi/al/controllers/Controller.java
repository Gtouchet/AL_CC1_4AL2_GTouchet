package esgi.al.controllers;

import esgi.al.exceptions.modelsExceptions.InvalidAddressParameter;
import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;

import java.util.stream.Stream;

public interface Controller<T>
{
    void post(T element) throws FailedToCreate, InvalidUserParameter, InvalidAddressParameter;
    Stream<T> get();
    T get(String id) throws ElementNotFound;
    void put(T element) throws ElementNotFound, FailedToCreate, InvalidUserParameter, InvalidAddressParameter;
    void del(String id) throws ElementNotFound;
}
