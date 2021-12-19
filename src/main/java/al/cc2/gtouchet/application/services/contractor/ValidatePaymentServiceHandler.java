package al.cc2.gtouchet.application.services.contractor;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.builders.ContractorBuilder;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidationException;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

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
