package esgi.al.cc1.inMemoryRepositories;

import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PaymentInMemoryRepository implements Repository<Payment>
{
    private final List<Payment> payments;

    public PaymentInMemoryRepository()
    {
        this.payments = new ArrayList<>();
    }

    @Override
    public void create(Payment payment)
    {
        this.payments.add(payment);
    }

    @Override
    public Stream<Payment> read()
    {
        return this.payments.stream();
    }

    @Override
    public Payment read(Id id)
    {
        return this.findById(id);
    }

    @Override
    public void update(Id id, Payment payment)
    {
        // Do nothing
    }

    @Override
    public void remove(Id id)
    {
        this.payments.remove(this.findById(id));
    }

    @Override
    public boolean exists(Id id)
    {
        return this.payments.stream().anyMatch(payment -> payment.getId().equals(id));
    }

    private Payment findById(Id id)
    {
        return this.payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
