package al.cc2.gtouchet.application.services.payment;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class ReadIdPaymentServiceHandler implements ServiceHandler<Payment, Id>
{
    private final Repository<Payment> paymentRepository;

    public ReadIdPaymentServiceHandler(Repository<Payment> paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment handle(Id command)
    {
        try {
            return this.paymentRepository.read(command);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
