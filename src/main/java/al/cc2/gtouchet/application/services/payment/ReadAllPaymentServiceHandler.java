package al.cc2.gtouchet.application.services.payment;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ReadAllPaymentServiceHandler implements ServiceHandler<List<Payment>, Void>
{
    private final Repository<Payment> paymentRepository;

    public ReadAllPaymentServiceHandler(Repository<Payment> paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> handle(Void command)
    {
        return this.paymentRepository.read().collect(Collectors.toList());
    }
}
