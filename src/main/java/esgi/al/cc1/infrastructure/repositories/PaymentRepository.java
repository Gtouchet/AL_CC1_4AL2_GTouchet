package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PaymentRepository implements Repository<Payment>
{
    private final List<Payment> payments;
    private final JsonAccessor<Payment> jsonAccessor;

    public PaymentRepository(JsonAccessor<Payment> jsonAccessor)
    {
        this.jsonAccessor = jsonAccessor;
        this.payments = this.getDataFromJsonFile();
    }

    private List<Payment> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonAccessor.getDataFromFile()));
    }

    @Override
    public void create(Payment element) throws FailedToCreate
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
    public void update(Payment element) throws ElementNotFound, FailedToUpdate
    {

    }

    @Override
    public void remove(String id) throws ElementNotFound
    {

    }
}
