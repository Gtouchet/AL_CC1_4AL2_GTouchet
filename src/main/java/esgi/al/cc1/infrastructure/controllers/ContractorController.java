package esgi.al.cc1.infrastructure.controllers;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.stream.Stream;

public class ContractorController implements Controller<Contractor>
{
    private final Repository<Contractor> contractorRepository;

    public ContractorController(Repository<Contractor> contractorRepository)
    {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public void create(String[] values) throws FailedToCreate
    {

    }

    @Override
    public Stream<Contractor> read()
    {
        return null;
    }

    @Override
    public Contractor read(String id) throws ElementNotFound
    {
        return null;
    }

    @Override
    public void update(Contractor element) throws ElementNotFound, FailedToUpdate
    {

    }

    @Override
    public void remove(String id) throws ElementNotFound
    {

    }
}
