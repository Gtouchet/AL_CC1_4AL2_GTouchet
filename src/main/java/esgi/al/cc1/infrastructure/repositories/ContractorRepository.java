package esgi.al.cc1.infrastructure.repositories;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.infrastructure.exceptions.repositoriesExceptions.FailedToUpdate;
import esgi.al.cc1.infrastructure.services.jsonServices.JsonDataAccessor;

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

    private void writeJsonFile()
    {
        this.jsonDataAccessor.writeInFile(this.contractors);
    }

    @Override
    public void create(Contractor contractor) throws FailedToCreate
    {
        Contractor registeredContractor = this.contractors.stream()
                .filter(c -> c.getLogin().equals(contractor.getLogin()))
                .findFirst()
                .orElse(null);

        if (registeredContractor != null)
        {
            throw new FailedToCreate(Contractor.class, "login already in use by user ID [" + registeredContractor.getId().toString() + "]");
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
    public Contractor read(String id) throws ElementNotFound
    {
        return this.findById(id);
    }

    @Override
    public void update(String id, Contractor contractor) throws ElementNotFound, FailedToUpdate
    {
        Contractor registeredContractor = this.findById(id);

        this.contractors.remove(registeredContractor);

        registeredContractor.setPassword(contractor.getPassword().toString());
        registeredContractor.setName(contractor.getName());
        registeredContractor.setPaymentMethod(contractor.getPaymentMethod());

        this.contractors.add(registeredContractor);

        this.writeJsonFile();
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        this.contractors.remove(this.findById(id));

        this.writeJsonFile();
    }

    @Override
    public void validatePayment(String id) throws ElementNotFound
    {
        Contractor registeredContractor = this.findById(id);

        this.contractors.remove(registeredContractor);
        registeredContractor.setPaymentValidated(true); // todo: mock validation with stubbed service
        this.contractors.add(registeredContractor);

        this.writeJsonFile();
    }

    @Override
    public void addWorker(String projectId, String workerId) throws ElementNotFound, FailedToUpdate
    {
        // Do nothing
    }

    @Override
    public void removeWorker(String projectId, String workerId) throws ElementNotFound, FailedToUpdate
    {
        // Do nothing
    }

    private Contractor findById(String id) throws ElementNotFound
    {
        Contractor registeredContractor = this.contractors.stream()
                .filter(c -> c.getId().toString().equals(id))
                .findFirst()
                .orElse(null);

        if (registeredContractor == null)
        {
            throw new ElementNotFound(Contractor.class, id);
        }

        return registeredContractor;
    }
}
