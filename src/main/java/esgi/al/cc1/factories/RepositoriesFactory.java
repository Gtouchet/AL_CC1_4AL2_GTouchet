package esgi.al.cc1.factories;

import esgi.al.cc1.repositories.PaymentRepository;
import esgi.al.cc1.repositories.Repository;
import esgi.al.cc1.repositories.UsersRepository;
import esgi.al.cc1.models.Payment;
import esgi.al.cc1.models.User;

public class RepositoriesFactory
{
    public Repository<User> createUserRepository()
    {
        return new UsersRepository(new JsonAccessorsFactory().createUserJsonAccessor());
    }

    public Repository<Payment> createPaymentRepository()
    {
        return new PaymentRepository(new JsonAccessorsFactory().createPaymentJsonAccessor());
    }
}