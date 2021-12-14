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
    public ServiceHandler<Payment, CreatePaymentDto> getCreatePaymentHandler()
    {
        return new CreatePaymentServiceHandler(
                new DataRepositoriesFactory().createPaymentRepository(),
                new DataRepositoriesFactory().createContractorRepository(),
                new DataRepositoriesFactory().createWorkerRepository()
        );
    }

    public ServiceHandler<List<Payment>, Void> getReadAllPaymentHandler()
    {
        return new ReadAllPaymentServiceHandler(
                new DataRepositoriesFactory().createPaymentRepository()
        );
    }

    public ServiceHandler<Payment, Id> getReadIdPaymentHandler()
    {
        return new ReadIdPaymentServiceHandler(
                new DataRepositoriesFactory().createPaymentRepository()
        );
    }

    public ServiceHandler<Boolean, Id> getDeletePaymentHandler()
    {
        return new DeletePaymentServiceHandler(
                new DataRepositoriesFactory().createPaymentRepository()
        );
    }
}
