package esgi.al.gtouchet.cc2.application.paymentServices;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.application.paymentServices.create.CreatePaymentDto;
import esgi.al.gtouchet.cc2.application.paymentServices.create.CreatePaymentServiceHandler;
import esgi.al.gtouchet.cc2.application.paymentServices.delete.DeletePaymentServiceHandler;
import esgi.al.gtouchet.cc2.application.paymentServices.read.ReadAllPaymentServiceHandler;
import esgi.al.gtouchet.cc2.application.paymentServices.read.ReadIdPaymentServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Payment;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.DataRepositoriesFactory;

import java.util.List;

public class PaymentServicesFactory
{
    private final DataRepositoriesFactory dataRepositoriesFactory;

    public PaymentServicesFactory(DataRepositoriesFactory dataRepositoriesFactory)
    {
        this.dataRepositoriesFactory = dataRepositoriesFactory;
    }

    public ServiceHandler<Payment, CreatePaymentDto> getCreatePaymentHandler()
    {
        return new CreatePaymentServiceHandler(
                this.dataRepositoriesFactory.createPaymentRepository(),
                this.dataRepositoriesFactory.createContractorRepository(),
                this.dataRepositoriesFactory.createWorkerRepository()
        );
    }

    public ServiceHandler<List<Payment>, Void> getReadAllPaymentHandler()
    {
        return new ReadAllPaymentServiceHandler(
                this.dataRepositoriesFactory.createPaymentRepository()
        );
    }

    public ServiceHandler<Payment, Id> getReadIdPaymentHandler()
    {
        return new ReadIdPaymentServiceHandler(
                this.dataRepositoriesFactory.createPaymentRepository()
        );
    }

    public ServiceHandler<Boolean, Id> getDeletePaymentHandler()
    {
        return new DeletePaymentServiceHandler(
                this.dataRepositoriesFactory.createPaymentRepository()
        );
    }
}
