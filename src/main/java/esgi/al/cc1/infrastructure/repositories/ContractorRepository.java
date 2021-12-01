package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.utilitaries.JsonDataAccessor;

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

    @Override
    public void create(Contractor contractor) throws FailedToCreateException
    {
        Contractor registeredContractor = this.contractors.stream()
                .filter(c -> c.getLogin().equals(contractor.getLogin()))
                .findFirst()
                .orElse(null);

        if (registeredContractor != null)
        {
            throw new FailedToCreateException(Contractor.class, "login already in use by user ID [" + registeredContractor.getId() + "]");
        }

        this.contractors.add(contractor);
        this.writeJsonFile();
    }

    @Override
    public Stream<Contractor> read()
    {
        return this.contractors.stream();
    }

    @Override
    public Contractor read(Id id) throws ElementNotFoundException
    {
        return this.findById(id);
    }

    @Override
    public void update(Id id, Contractor contractor) throws ElementNotFoundException, FailedToUpdateException
    {
        Contractor registeredContractor = this.findById(id);

        this.contractors.remove(registeredContractor);

        registeredContractor.setPassword(contractor.getPassword());
        registeredContractor.setName(contractor.getName());
        registeredContractor.setPaymentMethod(contractor.getPaymentMethod());
        registeredContractor.setPaymentValidated(contractor.isPaymentValidated());

        this.contractors.add(registeredContractor);
        this.writeJsonFile();
    }

    @Override
    public void remove(Id id) throws ElementNotFoundException
    {
        this.contractors.remove(this.findById(id));
        this.writeJsonFile();
    }

    @Override
    public boolean exists(Id id)
    {
        return this.contractors.stream().anyMatch(contractor -> contractor.getId().equals(id));
    }

    private List<Contractor> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonDataAccessor.getDataFromFile()));
    }

    private void writeJsonFile()
    {
        this.jsonDataAccessor.writeInFile(this.contractors);
    }

    private Contractor findById(Id id) throws ElementNotFoundException
    {
        Contractor registeredContractor = this.contractors.stream()
                .filter(contractor -> contractor.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredContractor == null)
        {
            throw new ElementNotFoundException(Contractor.class, id.toString());
        }

        return registeredContractor;
    }
}
