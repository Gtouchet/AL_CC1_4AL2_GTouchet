package esgi.al.cc1.infrastructure.factories;

import esgi.al.cc1.domain.models.Payment;
import esgi.al.cc1.domain.models.Worker;
import esgi.al.cc1.infrastructure.repositories.PaymentRepository;
import esgi.al.cc1.infrastructure.repositories.Repository;
import esgi.al.cc1.infrastructure.repositories.WorkerRepository;

public class RepositoriesFactory
{
    public Repository<Payment> createPaymentRepository()
    {
        return new PaymentRepository(new JsonAccessorsFactory().createPaymentJsonAccessor());
    }

    public Repository<Worker> createWorkerRepository()
    {
        return new WorkerRepository(new JsonAccessorsFactory().createWorkerJsonAccessor());
    }
}