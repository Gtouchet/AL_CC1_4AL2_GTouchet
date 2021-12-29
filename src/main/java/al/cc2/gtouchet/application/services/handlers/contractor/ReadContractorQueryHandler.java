package al.cc2.gtouchet.application.services.handlers.contractor;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.ReadContractorQuery;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.infrastructure.repositories.EntityNotFoundException;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

public final class ReadContractorQueryHandler implements QueryHandler<Contractor, ReadContractorQuery>
{
    private final Repository<Contractor> contractorRepository;

    public ReadContractorQueryHandler(Repository contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Contractor handle(ReadContractorQuery query)
    {
        try {
            return this.contractorRepository.read(query.id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
