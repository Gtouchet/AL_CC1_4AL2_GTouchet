package esgi.al.cc1.infrastructure.factories;

import esgi.al.cc1.infrastructure.repositories.PaymentRepository;
import esgi.al.cc1.infrastructure.repositories.Repository;
import esgi.al.cc1.infrastructure.repositories.ContractorRepository;
import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Contractor;

public class RepositoriesFactory
{
    public Repository<Contractor> createUserRepository()
    {
        return new ContractorRepository(new JsonAccessorsFactory().createUserJsonAccessor());
    }

    public Repository<Payment> createPaymentRepository()
    {
        return new PaymentRepository(new JsonAccessorsFactory().createPaymentJsonAccessor());
    }
}