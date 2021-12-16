package esgi.al.gtouchet.cc2.application.services.contractor;

import esgi.al.gtouchet.cc2.application.services.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

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
