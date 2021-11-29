package esgi.al.cc1.infrastructure.controllers;

import java.util.stream.Stream;

public interface Controller<T>
{
    void create(String[] values);
    Stream<T> read();
    T read(String id);
    void update(String[] values);
    void remove(String id);

    void validatePayment(String id);
}
