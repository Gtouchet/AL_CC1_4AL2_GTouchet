package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.services.JsonDataAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PaymentRepository implements Repository<Payment>
{
    private final List<Payment> payments;
    private final JsonDataAccessor<Payment> jsonDataAccessor;

    public PaymentRepository(JsonDataAccessor<Payment> jsonDataAccessor)
    {
        this.jsonDataAccessor = jsonDataAccessor;
        this.payments = this.getDataFromJsonFile();
    }

    private List<Payment> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonDataAccessor.getDataFromFile()));
    }

    private void writeJsonFile()
    {
        this.jsonDataAccessor.writeInFile(this.payments);
    }

    @Override
    public void create(Payment payment) throws FailedToCreate
    {
        this.payments.add(payment);

        this.writeJsonFile();
    }

    @Override
    public Stream<Payment> read()
    {
        return this.payments.stream();
    }

    @Override
    public Payment read(String id) throws ElementNotFound
    {
        return this.findById(id);
    }

    @Override
    public void update(String id, Payment payment) throws ElementNotFound, FailedToUpdate
    {
        // Do nothing
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.payments.remove(this.findById(id));

        this.writeJsonFile();
    }

    @Override
    public void validatePayment(String id) throws ElementNotFound
    {
        // Do nothing
    }

    @Override
    public void addWorker(String projectId, String workerId) throws ElementNotFound, FailedToUpdate
    {
        // Do nothing
    }

    @Override
    public void removeWorker(String projectId, String workerId) throws ElementNotFound, FailedToUpdate
    {
        // Do nothing
    }

    private Payment findById(String id) throws ElementNotFound
    {
        Payment registeredPayment = this.payments.stream()
                .filter(p -> p.getId().toString().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredPayment == null)
        {
            throw new ElementNotFound(Payment.class, id);
        }

        return registeredPayment;
    }
}
