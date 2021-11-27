package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonDataAccessor;
import esgi.al.cc1.domain.models.Contractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ContractorRepository implements Repository<Contractor>
{
    private final List<Contractor> contractors;
    private final JsonDataAccessor<Contractor> jsonDataAccessor;

    public ContractorRepository(JsonDataAccessor<Contractor> jsonDataAccessor)
    {
        this.jsonDataAccessor = jsonDataAccessor;
        this.contractors = this.getDataFromJsonFile();
    }

    private List<Contractor> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonDataAccessor.getDataFromFile()));
    }

    @Override
    public void create(Contractor element) throws FailedToCreate
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
