package esgi.al.cc1.factories;

import esgi.al.cc1.repositories.PaymentRepository;
import esgi.al.cc1.repositories.Repository;
import esgi.al.cc1.repositories.UsersRepository;
import esgi.al.cc1.utilitaries.Globals;
import esgi.al.cc1.models.Payment;
import esgi.al.cc1.models.User;

public class RepositoriesFactory
{
    public Repository<User> createUserRepository()
    {
        return new UsersRepository(Globals.JSON_USERS_FILE_PATH);
    }

    public Repository<Payment> createPaymentController()
    {
        return new PaymentRepository(Globals.JSON_PAYMENTS_FILE_PATH);
    }
}