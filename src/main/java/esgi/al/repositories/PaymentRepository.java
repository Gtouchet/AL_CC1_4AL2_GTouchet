package esgi.al.repositories;

import esgi.al.exceptions.repositoriesExceptions.ElementNotFound;
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
    public void post(Payment element)
    {
        this.payments.add(element);

        this.jsonHelper.writeInFile(this.payments);
    }

    @Override
    public Stream<Payment> get()
    {
        return this.payments.stream();
    }

    @Override
    public Payment get(String id) throws ElementNotFound
    {
        return this.findPaymentById(id);
    }

    @Override
    public void put(Payment payment)
    {
        // Do nothing, we do not update a payment after it's been registered
        // This code is unreachable
    }

    @Override
    public void del(String id) throws ElementNotFound
    {
        Payment payment = this.findPaymentById(id);

        this.payments.remove(payment);

        this.jsonHelper.writeInFile(this.payments);
    }

    private Payment findPaymentById(String id) throws ElementNotFound
    {
        Payment payment = this.payments.stream()
                .filter(streamPayment -> streamPayment.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (payment == null)
        {
            throw new ElementNotFound(id);
        }

        return payment;
    }
}
