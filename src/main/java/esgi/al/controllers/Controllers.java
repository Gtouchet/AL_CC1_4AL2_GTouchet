package esgi.al.controllers;

import java.util.stream.Stream;

public interface Controllers<T, U>
{
    void post(U element);
    Stream<T> get();
    T get(String id);
    void put(String id, U element);
    void del(String id);
}
