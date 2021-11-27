package esgi.al.cc1.infrastructure.controllers;

import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.stream.Stream;

public class PaymentController implements Controller<Payment>
{
    private final Repository<Payment> paymentRepository;

    public PaymentController(Repository<Payment> paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void create(String[] values) throws FailedToCreate
    {

    }

    @Override
    public Stream<Payment> read()
    {
        return null;
    }

    @Override
    public Payment read(String id) throws ElementNotFound
    {
        return null;
    }

    @Override
    public void update(String[] values) throws ElementNotFound, FailedToUpdate
    {

    }

    @Override
    public void remove(String id) throws ElementNotFound
    {

    }
}
