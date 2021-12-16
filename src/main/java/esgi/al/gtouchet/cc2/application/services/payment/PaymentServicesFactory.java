package esgi.al.gtouchet.cc2.application.services.payment;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.application.services.payment.create.CreatePaymentDto;
import esgi.al.gtouchet.cc2.application.services.payment.create.CreatePaymentServiceHandler;
import esgi.al.gtouchet.cc2.application.services.payment.delete.DeletePaymentServiceHandler;
import esgi.al.gtouchet.cc2.application.services.payment.read.ReadAllPaymentServiceHandler;
import esgi.al.gtouchet.cc2.application.services.payment.read.ReadIdPaymentServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.factories.RepositoriesFactory;

import java.util.List;

public class PaymentServicesFactory
{
    private final RepositoriesFactory repositoriesFactory;

    public PaymentServicesFactory(RepositoriesFactory repositoriesFactory)
    {
        this.repositoriesFactory = repositoriesFactory;
    }

    public ServiceHandler<Payment, CreatePaymentDto> getCreatePaymentHandler()
    {
        return new CreatePaymentServiceHandler(
                this.repositoriesFactory.createPaymentRepository(),
                this.repositoriesFactory.createContractorRepository(),
                this.repositoriesFactory.createWorkerRepository()
        );
    }

    public ServiceHandler<List<Payment>, Void> getReadAllPaymentHandler()
    {
        return new ReadAllPaymentServiceHandler(
                this.repositoriesFactory.createPaymentRepository()
        );
    }

    public ServiceHandler<Payment, Id> getReadIdPaymentHandler()
    {
        return new ReadIdPaymentServiceHandler(
                this.repositoriesFactory.createPaymentRepository()
        );
    }

    public ServiceHandler<Boolean, Id> getDeletePaymentHandler()
    {
        return new DeletePaymentServiceHandler(
                this.repositoriesFactory.createPaymentRepository()
        );
    }
}
