package esgi.al.cc1.application;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;
import esgi.al.cc1.infrastructure.repositories.ElementNotFoundException;
import esgi.al.cc1.infrastructure.repositories.FailedToCreateException;
import esgi.al.cc1.infrastructure.repositories.FailedToUpdateException;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ContractorServiceImpl implements ContractorService
{
    private final Repository<Contractor> contractorRepository;
    private final Repository<Worker> workerRepository;

    public ContractorServiceImpl(
            Repository<Contractor> contractorRepository,
            Repository<Worker> workerRepository
    )
    {
        this.contractorRepository = contractorRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public void create(String login, Password password, String name, PaymentMethod paymentMethod)
    {
        Worker registeredWorker = this.workerRepository.read()
                .filter(worker -> worker.getLogin().equals(login))
                .findFirst()
                .orElse(null);
        if (registeredWorker != null)
        {
            System.out.println("Error: login already in use by user ID [" + registeredWorker.getId() + "]");
            return;
        }

        try {
            this.contractorRepository.create(Contractor.of(
                    Id.generate(),
                    login,
                    password,
                    name,
                    paymentMethod,
                    false,
                    Date.now()
            ));
        } catch (FailedToCreateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void read()
    {
        List<Contractor> contractors = this.contractorRepository.read().collect(Collectors.toList());

        if (contractors.size() == 0)
        {
            System.out.println("No Contractor registered yet");
        }
        else
        {
            contractors.forEach(System.out::println);
        }
    }

    @Override
    public void read(Id id)
    {
        try {
            System.out.println(this.contractorRepository.read(id));
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Id id, Password password, String name, PaymentMethod paymentMethod)
    {
        Contractor contractor;
        try {
            contractor = this.contractorRepository.read(id);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            this.contractorRepository.update(id, Contractor.of(
                    contractor.getId(),
                    contractor.getLogin(),
                    password,
                    name,
                    paymentMethod,
                    contractor.isPaymentValidated(),
                    contractor.getCreationDate()
            ));
        } catch (ElementNotFoundException | FailedToUpdateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Id id)
    {
        try {
            this.contractorRepository.remove(id);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void validatePayment(Id id)
    {
        Contractor contractor;
        try {
            contractor = this.contractorRepository.read(id);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        // todo: call the mock API payment validation here

        try {
            this.contractorRepository.update(id, Contractor.of(
                    contractor.getId(),
                    contractor.getLogin(),
                    contractor.getPassword(),
                    contractor.getName(),
                    contractor.getPaymentMethod(),
                    true,
                    contractor.getCreationDate()
            ));
        } catch (ElementNotFoundException | FailedToUpdateException e) {
            System.out.println(e.getMessage());;
        }
    }
}
