package esgi.al.gtouchet.cc2.application.services.payment.read;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
