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
    public void post(Payment payment) throws InvalidModelParameter, FailedToCreate
    {
        PaymentValidator.validate(payment);

        this.paymentRepository.post(payment);
    }

    @Override
    public Stream<Payment> get()
    {
        return this.paymentRepository.get();
    }

    @Override
    public Payment get(String id) throws ElementNotFound
    {
        return this.paymentRepository.get(id);
    }

    @Override
    public void put(Payment payment) throws ElementNotFound, FailedToCreate, InvalidModelParameter
    {
        PaymentValidator.validate(payment);

        this.paymentRepository.put(payment);
    }

    @Override
    public void del(String id) throws ElementNotFound
    {
        this.paymentRepository.del(id);
    }
}
