package esgi.al.cc1.controllers;

import esgi.al.cc1.exceptions.modelsExceptions.InvalidModelParameter;
import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.repositories.Repository;
import esgi.al.cc1.services.validatorServices.PaymentValidator;

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
        // Do nothing
        // This code is unreachable
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.paymentRepository.remove(id);
    }
}
