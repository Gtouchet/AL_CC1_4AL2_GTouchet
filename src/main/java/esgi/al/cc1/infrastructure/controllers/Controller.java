package esgi.al.cc1.infrastructure.controllers;

import esgi.al.cc1.domain.dtos.Id;

import java.util.stream.Stream;

public interface Controller<T>
{
    Id create(String[] values);
    Stream<T> read();
    T read(String id);
    void update(String[] values);
    void remove(String id);

    void validatePayment(String id);

    void addWorker(String[] values);
    void removeWorker(String[] values);
}
