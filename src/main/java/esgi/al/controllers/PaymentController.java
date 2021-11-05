package esgi.al.controllers;

import esgi.al.exceptions.modelsExceptions.InvalidAddressParameter;
import esgi.al.exceptions.modelsExceptions.InvalidUserParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.models.Payment;
import esgi.al.repositories.Repository;

import java.util.stream.Stream;

public class PaymentController implements Controller<Payment>
{
    private final Repository<Payment> paymentRepository;

    public PaymentController(Repository<Payment> paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void post(Payment element) throws FailedToCreate, InvalidUserParameter, InvalidAddressParameter
    {

    }

    @Override
    public Stream<Payment> get()
    {
        return null;
    }

    @Override
    public Payment get(String id) throws ElementNotFound
    {
        return null;
    }

    @Override
    public void put(Payment element) throws ElementNotFound, FailedToCreate, InvalidUserParameter, InvalidAddressParameter
    {

    }

    @Override
    public void del(String id) throws ElementNotFound
    {

    }
}
