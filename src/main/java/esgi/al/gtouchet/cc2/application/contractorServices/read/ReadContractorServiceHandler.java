package esgi.al.gtouchet.cc2.application.contractorServices.read;

import esgi.al.gtouchet.cc2.application.ServiceHandler;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.repositories.EntityNotFoundException;
import esgi.al.gtouchet.cc2.infrastructure.repositories.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReadContractorServiceHandler implements ServiceHandler<List<Contractor>, Id>
{
    private final Repository<Contractor> contractorRepository;

    public ReadContractorServiceHandler(Repository<Contractor> contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public List<Contractor> handle(Id command)
    {
        try {
            return command != null ?
                    Collections.singletonList(this.contractorRepository.read(command)) :
                    this.contractorRepository.read().collect(Collectors.toList());

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
