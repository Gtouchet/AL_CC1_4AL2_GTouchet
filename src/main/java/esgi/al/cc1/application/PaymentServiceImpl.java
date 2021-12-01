package esgi.al.cc1.application;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.repositories.ElementNotFoundException;
import esgi.al.cc1.infrastructure.repositories.FailedToCreateException;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService
{
    private final Repository<Payment> paymentRepository;
    private final Repository<Contractor> contractorRepository;
    private final Repository<Worker> workerRepository;

    public PaymentServiceImpl(
            Repository<Payment> paymentRepository,
            Repository<Contractor> contractorRepository,
            Repository<Worker> workerRepository)
    {
        this.paymentRepository = paymentRepository;
        this.contractorRepository = contractorRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public Id create(Id contractorId, Id workerId, double amount, String reason)
    {
        Contractor contractor;
        try {
            contractor = this.contractorRepository.read(contractorId);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

        if (!contractor.isPaymentValidated())
        {
            System.out.println(
                    "Error: Contractor ID [" + contractorId + "] payment method is not validated yet, " +
                    "please validate it before trying to make a payment."
            );
            return null;
        }

        if (!this.workerRepository.exists(workerId))
        {
            System.out.println("Error: no Worker registered with ID [" + workerId + "]");
            return null;
        }

        try {
            Payment payment = Payment.of(
                    Id.generate(),
                    contractorId,
                    workerId,
                    contractor.getPaymentMethod(),
                    amount,
                    reason,
                    Date.now()
            );
            this.paymentRepository.create(payment);
            return payment.getId();

        } catch (FailedToCreateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void read()
    {
        List<Payment> payments = this.paymentRepository.read().collect(Collectors.toList());

        if (payments.size() == 0)
        {
            System.out.println("No Payment registered yet");
        }
        else
        {
            payments.forEach(System.out::println);
        }
    }

    @Override
    public void read(Id id)
    {
        try {
            System.out.println(this.paymentRepository.read(id));
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Id id)
    {
        try {
            this.paymentRepository.remove(id);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
