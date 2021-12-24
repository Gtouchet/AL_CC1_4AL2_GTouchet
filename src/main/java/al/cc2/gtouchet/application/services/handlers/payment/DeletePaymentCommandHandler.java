package al.cc2.gtouchet.application.services.handlers.payment;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.payment.DeletePaymentCommand;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class DeletePaymentCommandHandler implements CommandHandler<Boolean, DeletePaymentCommand>
{
    private final Repository<Payment> paymentRepository;

    public DeletePaymentCommandHandler(Repository<Payment> paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Boolean handle(DeletePaymentCommand command)
    {
        try {
            this.paymentRepository.remove(command.id);
            return true;
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
