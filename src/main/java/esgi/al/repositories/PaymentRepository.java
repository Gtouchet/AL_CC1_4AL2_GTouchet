package esgi.al.repositories;

import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.models.Payment;
import esgi.al.utilitaries.JsonHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PaymentRepository implements Repository<Payment>
{
    private final List<Payment> payments;

    private final JsonHelper<Payment> jsonHelper;

    public PaymentRepository(String jsonFilePath)
    {
        this.jsonHelper = new JsonHelper<>(Payment.class, jsonFilePath);

        this.payments = this.getDataFromJsonFile();
    }

    private List<Payment> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonHelper.getDataFromFile()));
    }

    @Override
    public void post(Payment element) throws FailedToCreate
    {

    }

    @Override
    public Stream<Payment> get() {
        return null;
    }

    @Override
    public Payment get(String id) throws ElementNotFound
    {
        return null;
    }

    @Override
    public void put(Payment element) throws ElementNotFound, FailedToCreate
    {

    }

    @Override
    public void del(String id) throws ElementNotFound
    {

    }
}
