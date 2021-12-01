package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.utilitaries.JsonDataAccessor;

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

    @Override
    public void create(Payment payment) throws FailedToCreateException
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
    public Payment read(Id id) throws ElementNotFoundException
    {
        return this.findById(id);
    }

    @Override
    public void update(Id id, Payment element) throws ElementNotFoundException, FailedToUpdateException
    {
        // Do nothing
    }

    @Override
    public void remove(Id id) throws ElementNotFoundException
    {
        this.payments.remove(this.findById(id));
        this.writeJsonFile();
    }

    @Override
    public boolean exists(Id id)
    {
        return this.payments.stream().anyMatch(payment -> payment.getId().equals(id));
    }

    @Override
    public void writeJsonFile()
    {
        this.jsonDataAccessor.writeInFile(this.payments);
    }

    private Payment findById(Id id) throws ElementNotFoundException
    {
        Payment registeredPayment = this.payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredPayment == null)
        {
            throw new ElementNotFoundException(Payment.class, id.toString());
        }

        return registeredPayment;
    }
}
