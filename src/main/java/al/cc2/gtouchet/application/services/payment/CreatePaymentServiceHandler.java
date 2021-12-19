package al.cc2.gtouchet.application.services.payment;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.application.services.payment.dtos.CreatePaymentDto;
import al.cc2.gtouchet.domain.builders.PaymentBuilder;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.models.Worker;
import al.cc2.gtouchet.domain.valueObjects.Date;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class CreatePaymentServiceHandler implements ServiceHandler<Payment, CreatePaymentDto>
{
    private final Repository<Payment> paymentRepository;
    private final Repository<Contractor> contractorRepository;
    private final Repository<Worker> workerRepository;

    public CreatePaymentServiceHandler(
            Repository<Payment> paymentRepository,
            Repository<Contractor> contractorRepository,
            Repository<Worker> workerRepository)
    {
        this.paymentRepository = paymentRepository;
        this.contractorRepository = contractorRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public Payment handle(CreatePaymentDto command)
    {
        try {
            Contractor contractor = this.contractorRepository.read(command.contractorId);

            if (!contractor.isPaymentValidated())
            {
                System.out.println(
                        "Error: Contractor ID [" + command.contractorId + "] payment method is not validated yet, " +
                        "please validate it before trying to make a payment."
                );
                return null;
            }

            if (!this.workerRepository.exists(command.workerId))
            {
                System.out.println("Error: no Worker registered with ID [" + command.workerId + "]");
                return null;
            }

            Payment payment = PaymentBuilder.init(Id.generate(), Date.now())
                    .setContractorId(command.contractorId)
                    .setWorkerId(command.workerId)
                    .setPaymentMethod(contractor.getPaymentMethod())
                    .setAmount(command.amount)
                    .setReason(command.reason)
                    .build();

            this.paymentRepository.create(payment);

            return payment;

        } catch (EntityNotFoundException | NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
