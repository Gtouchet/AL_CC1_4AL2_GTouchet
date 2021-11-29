package esgi.al.cc1.infrastructure.controllers;

import esgi.al.cc1.domain.dtos.Date;
import esgi.al.cc1.domain.dtos.Id;
import esgi.al.cc1.domain.dtos.Password;
import esgi.al.cc1.domain.enumerators.PaymentMethod;
import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.stream.Stream;

public class ContractorController implements Controller<Contractor>
{
    private final Repository<Contractor> contractorRepository;

    public ContractorController(Repository<Contractor> contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public void create(String[] values)
    {
        try {
            PaymentMethod paymentMethod = PaymentMethod.valueOf(values[4].toLowerCase());

            this.contractorRepository.create(Contractor.of(
                        Id.generate(),
                        values[1],
                        Password.of(values[2]),
                        values[3],
                        paymentMethod,
                        Date.now()
            ));
        } catch (FailedToCreate e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown payment method [" + values[4] + "]");
        }
    }

    @Override
    public Stream<Contractor> read()
    {
        return this.contractorRepository.read();
    }

    @Override
    public Contractor read(String id)
    {
        try {
            return this.contractorRepository.read(id);
        } catch (ElementNotFound e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(String[] values)
    {
        try {
            PaymentMethod paymentMethod = PaymentMethod.valueOf(values[4].toLowerCase());

            this.contractorRepository.update(values[1].toLowerCase(), Contractor.of(
                    null,
                    null,
                    Password.of(values[2]),
                    values[3],
                    paymentMethod,
                    null
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown payment method [" + values[4] + "]");
        } catch (ElementNotFound | FailedToUpdate e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(String id)
    {
        try {
            this.contractorRepository.read(id);
        } catch (ElementNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void validatePayment(String id)
    {
        try {
            this.contractorRepository.validatePayment(id);
        } catch (ElementNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}
