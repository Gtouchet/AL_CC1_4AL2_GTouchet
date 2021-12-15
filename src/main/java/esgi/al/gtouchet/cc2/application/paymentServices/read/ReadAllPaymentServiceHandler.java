package esgi.al.gtouchet.cc2.application.paymentServices.read;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
