package esgi.al.controllers;

import esgi.al.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.models.Payment;
import esgi.al.repositories.Repository;
import esgi.al.validators.PaymentValidator;

import java.util.stream.Stream;

public class PaymentController implements Controller<Payment>
{
    private final Repository<Payment> paymentRepository;

    public PaymentController(Repository<Payment> paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void create(Payment payment) throws InvalidModelParameter, FailedToCreate
    {
        PaymentValidator.validate(payment);

        this.paymentRepository.create(payment);
    }

    @Override
    public Stream<Payment> read()
    {
        return this.paymentRepository.read();
    }

    @Override
    public Payment read(String id) throws ElementNotFound
    {
        return this.paymentRepository.read(id);
    }

    @Override
    public void update(Payment payment)
    {
        // Do nothing, we do not update a payment after it's been registered
        // This code is unreachable
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.paymentRepository.remove(id);
    }
}
