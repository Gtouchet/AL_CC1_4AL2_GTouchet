package al.cc2.gtouchet.application.services.handlers.contractor;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.ReadAllContractorQuery;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ReadAllContractorQueryHandler implements QueryHandler<List<Contractor>, ReadAllContractorQuery>
{
    private final Repository<Contractor> contractorRepository;

    public ReadAllContractorQueryHandler(Repository<Contractor> contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public List<Contractor> handle(ReadAllContractorQuery query)
    {
        return this.contractorRepository.read().collect(Collectors.toList());
    }
}
