package esgi.al.gtouchet.cc2.application;

import esgi.al.gtouchet.cc2.domain.builders.ContractorBuilder;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.models.Worker;
import esgi.al.gtouchet.cc2.domain.validators.PasswordFormatException;
import esgi.al.gtouchet.cc2.domain.validators.PasswordValidator;
import esgi.al.gtouchet.cc2.domain.valueObjects.Date;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidationException;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ContractorServiceImpl implements ContractorService
{
    private final Repository<Contractor> contractorRepository;
    private final Repository<Worker> workerRepository;
    private final PaymentMethodValidatorApi paymentMethodValidatorApi;
    private final PasswordValidator passwordValidator;

    public ContractorServiceImpl(
            Repository<Contractor> contractorRepository,
            Repository<Worker> workerRepository,
            PaymentMethodValidatorApi paymentMethodValidatorApi,
            PasswordValidator passwordValidator)
    {
        this.contractorRepository = contractorRepository;
        this.workerRepository = workerRepository;
        this.paymentMethodValidatorApi = paymentMethodValidatorApi;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public Id create(String login, Password password, String name, PaymentMethod paymentMethod)
    {
        if (this.workerRepository.read().anyMatch(worker -> worker.getLogin().equals(login)) ||
            this.contractorRepository.read().anyMatch(contractor -> contractor.getLogin().equals(login)))
        {
            System.out.println("Error: login already in use");
            return null;
        }

        try {
            this.passwordValidator.validate(password);

            Contractor contractor = ContractorBuilder.init(Id.generate(), login, Date.now())
                    .setPassword(password)
                    .setName(name)
                    .setPaymentMethod(paymentMethod)
                    .build();

            this.contractorRepository.create(contractor);

            return contractor.getId();

        } catch (PasswordFormatException | NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
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
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Id id, Password password, String name, PaymentMethod paymentMethod)
    {
        try {
            Contractor contractor = this.contractorRepository.read(id);

            this.passwordValidator.validate(password);

            contractor = ContractorBuilder.init(contractor)
                    .setPassword(password)
                    .setName(name)
                    .setPaymentMethod(paymentMethod)
                    .build();

            this.contractorRepository.update(id, contractor);

        } catch (EntityNotFoundException | PasswordFormatException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Id id)
    {
        try {
            this.contractorRepository.remove(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public long getRepositorySize()
    {
        return this.contractorRepository.read().count();
    }

    @Override
    public boolean exists(Id id)
    {
        return this.contractorRepository.exists(id);
    }

    @Override
    public void validatePayment(Id id)
    {
        try {
            Contractor contractor = this.contractorRepository.read(id);

            if (contractor.isPaymentValidated())
            {
                System.out.println("Error: payment method already validated for Contractor ID [" + id + "]");
                return;
            }

            this.paymentMethodValidatorApi.validate(contractor.getPaymentMethod());

            contractor = ContractorBuilder.init(contractor)
                    .setIsPaymentValidated(true)
                    .build();

            this.contractorRepository.update(id, contractor);

        } catch (EntityNotFoundException | PaymentMethodValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
