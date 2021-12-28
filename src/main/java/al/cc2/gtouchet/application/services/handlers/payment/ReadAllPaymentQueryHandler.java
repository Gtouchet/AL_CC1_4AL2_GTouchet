package al.cc2.gtouchet.application.services.handlers.payment;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.payment.ReadAllPaymentQuery;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ReadAllPaymentQueryHandler implements QueryHandler<List<Payment>, ReadAllPaymentQuery>
{
    private final Repository<Payment> paymentRepository;

    public ReadAllPaymentQueryHandler(Repository paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> handle(ReadAllPaymentQuery query)
    {
        return this.paymentRepository.read().collect(Collectors.toList());
    }
}
