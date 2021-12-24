package al.cc2.gtouchet.application.services.handlers.payment;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.payment.ReadPaymentQuery;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class ReadPaymentQueryHandler implements QueryHandler<Payment, ReadPaymentQuery>
{
    private final Repository<Payment> paymentRepository;

    public ReadPaymentQueryHandler(Repository<Payment> paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment handle(ReadPaymentQuery query)
    {
        try {
            return this.paymentRepository.read(query.id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}