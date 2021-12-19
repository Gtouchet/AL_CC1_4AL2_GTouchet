package al.cc2.gtouchet.application.services.payment;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class DeletePaymentServiceHandler implements ServiceHandler<Boolean, Id>
{
    private final Repository<Payment> paymentRepository;

    public DeletePaymentServiceHandler(Repository<Payment> paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Boolean handle(Id command)
    {
        try {
            this.paymentRepository.remove(command);
            return true;
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
