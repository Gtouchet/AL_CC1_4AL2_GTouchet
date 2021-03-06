package al.cc2.gtouchet.application.services.handlers.contractor;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.ReadByPaymentContractorQuery;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public final class ReadContractorByPaymentQueryHandler implements QueryHandler<List<Contractor>, ReadByPaymentContractorQuery>
{
    public final Repository<Contractor> contractorRepository;

    public ReadContractorByPaymentQueryHandler(Repository contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public List<Contractor> handle(ReadByPaymentContractorQuery query)
    {
        return this.contractorRepository.read()
                .filter(contractor -> contractor.getPaymentMethod().equals(query.paymentMethod))
                .collect(Collectors.toList());
    }
}
