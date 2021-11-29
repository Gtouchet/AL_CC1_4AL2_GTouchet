package esgi.al.cc1.infrastructure.controllers;

import esgi.al.cc1.domain.enumerators.PaymentMethod;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.infrastructure.exceptions.modelsExceptions.ContractorPaymentNotValidated;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.stream.Stream;

public class PaymentController implements Controller<Payment>
{
    private final Repository<Payment> paymentRepository;
    // Used to get the contractor's payment method and if their payment method is validated
    private final Repository<Contractor> contractorRepository;

    public PaymentController(
            Repository<Payment> paymentRepository,
            Repository<Contractor> contractorRepository)
    {
        this.paymentRepository = paymentRepository;
        this.contractorRepository = contractorRepository;
    }

    @Override
    public void create(String[] values)
    {
        String contractorId = values[1].toLowerCase();
        try {
            if (!this.contractorRepository.read(contractorId).isPaymentValidated())
            {
                throw new ContractorPaymentNotValidated(contractorId);
            }

            PaymentMethod paymentMethod = this.contractorRepository.read(contractorId).getPaymentMethod();
            float amount = Float.parseFloat(values[3]);

            this.paymentRepository.create(Payment.of(
                    contractorId,
                    values[2].toLowerCase(),
                    paymentMethod,
                    amount
            ));
        } catch (NumberFormatException e) {
            System.out.println("Error: impossible to parse [" + values[4] + "] as an amount");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown payment method [" + values[3] + "]");
        } catch (ElementNotFound | FailedToCreate | ContractorPaymentNotValidated e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Stream<Payment> read()
    {
        return this.paymentRepository.read();
    }

    @Override
    public Payment read(String id)
    {
        try {
            return this.paymentRepository.read(id);
        } catch (ElementNotFound e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(String[] values)
    {
        // Do nothing
    }

    @Override
    public void remove(String id)
    {
        try {
            this.paymentRepository.remove(id);
        } catch (ElementNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void validatePayment(String id)
    {
        // Do nothing
    }

    @Override
    public void addWorker(String[] values)
    {
        // Do nothing
    }

    @Override
    public void removeWorker(String[] values)
    {
        // Do nothing
    }
}
