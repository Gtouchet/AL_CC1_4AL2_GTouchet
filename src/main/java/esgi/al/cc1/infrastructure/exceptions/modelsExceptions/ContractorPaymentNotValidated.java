package esgi.al.cc1.infrastructure.exceptions.modelsExceptions;

public class ContractorPaymentNotValidated extends Exception
{
    public ContractorPaymentNotValidated(String id)
    {
        super("Error: contractor ID [" + id + "] payment method not validated yet, please validate it before trying to make a payment.");
    }
}
