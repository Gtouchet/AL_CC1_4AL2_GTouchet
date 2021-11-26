package esgi.al.cc1.factories;

import esgi.al.cc1.repositories.PaymentRepository;
import esgi.al.cc1.repositories.Repository;
import esgi.al.cc1.repositories.TradesmanRepository;
import esgi.al.cc1.models.Payment;
import esgi.al.cc1.models.Tradesman;

public class RepositoriesFactory
{
    public Repository<Tradesman> createUserRepository()
    {
        return new TradesmanRepository(new JsonAccessorsFactory().createUserJsonAccessor());
    }

    public Repository<Payment> createPaymentRepository()
    {
        return new PaymentRepository(new JsonAccessorsFactory().createPaymentJsonAccessor());
    }
}