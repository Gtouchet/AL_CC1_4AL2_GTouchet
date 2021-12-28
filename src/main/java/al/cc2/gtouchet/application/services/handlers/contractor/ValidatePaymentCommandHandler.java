package al.cc2.gtouchet.application.services.handlers.contractor;

import al.cc2.gtouchet.application.kernel.CommandHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.ValidatePaymentCommand;
import al.cc2.gtouchet.domain.builders.ContractorBuilder;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidationException;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public class ValidatePaymentCommandHandler implements CommandHandler<Boolean, ValidatePaymentCommand>
{
    private final Repository<Contractor> contractorRepository;
    private final PaymentMethodValidatorApi paymentMethodValidatorApi;

    public ValidatePaymentCommandHandler(
            Repository contractorRepository,
            PaymentMethodValidatorApi paymentMethodValidatorApi)
    {
        this.contractorRepository = contractorRepository;
        this.paymentMethodValidatorApi = paymentMethodValidatorApi;
    }

    @Override
    public Boolean handle(ValidatePaymentCommand command)
    {
        try {
            Contractor contractor = this.contractorRepository.read(command.id);

            if (contractor.isPaymentValidated())
            {
                System.out.println("Error: payment method already validated for Contractor ID [" + command.id + "]");
                return false;
            }

            this.paymentMethodValidatorApi.validate(contractor.getPaymentMethod());

            contractor = ContractorBuilder.init(contractor)
                    .setIsPaymentValidated(true)
                    .build();

            this.contractorRepository.update(command.id, contractor);

            return true;

        } catch (EntityNotFoundException | PaymentMethodValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
