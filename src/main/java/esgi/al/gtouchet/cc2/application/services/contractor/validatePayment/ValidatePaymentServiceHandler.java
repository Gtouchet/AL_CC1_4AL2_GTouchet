package esgi.al.gtouchet.cc2.application.services.contractor.validatePayment;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.builders.ContractorBuilder;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidationException;
import esgi.al.gtouchet.cc2.infrastructure.apis.PaymentMethodValidatorApi;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

public class ValidatePaymentServiceHandler implements ServiceHandler<Boolean, Id>
{
    private final Repository<Contractor> contractorRepository;
    private final PaymentMethodValidatorApi paymentMethodValidatorApi;

    public ValidatePaymentServiceHandler(
            Repository<Contractor> contractorRepository,
            PaymentMethodValidatorApi paymentMethodValidatorApi)
    {
        this.contractorRepository = contractorRepository;
        this.paymentMethodValidatorApi = paymentMethodValidatorApi;
    }

    @Override
    public Boolean handle(Id command)
    {
        try {
            Contractor contractor = this.contractorRepository.read(command);

            if (contractor.isPaymentValidated())
            {
                System.out.println("Error: payment method already validated for Contractor ID [" + command + "]");
                return false;
            }

            this.paymentMethodValidatorApi.validate(contractor.getPaymentMethod());

            contractor = ContractorBuilder.init(contractor)
                    .setIsPaymentValidated(true)
                    .build();

            this.contractorRepository.update(command, contractor);

            return true;

        } catch (EntityNotFoundException | PaymentMethodValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
