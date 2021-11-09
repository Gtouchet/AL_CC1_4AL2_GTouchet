package esgi.al.factories;

import esgi.al.models.Payment;
import esgi.al.models.User;
import esgi.al.repositories.PaymentRepository;
import esgi.al.repositories.Repository;
import esgi.al.repositories.UsersRepository;
import esgi.al.utilitaries.Globals;

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