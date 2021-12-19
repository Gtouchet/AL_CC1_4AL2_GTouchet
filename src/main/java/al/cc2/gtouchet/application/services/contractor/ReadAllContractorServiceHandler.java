package al.cc2.gtouchet.application.services.contractor;

import al.cc2.gtouchet.application.services.ServiceHandler;
import al.cc2.gtouchet.domain.models.Contractor;
import al.cc2.gtouchet.infrastructure.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ReadAllContractorServiceHandler implements ServiceHandler<List<Contractor>, Void>
{
    private final Repository<Contractor> contractorRepository;

    public ReadAllContractorServiceHandler(Repository<Contractor> contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public List<Contractor> handle(Void ignore)
    {
        return this.contractorRepository.read().collect(Collectors.toList());
    }
}
