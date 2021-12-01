package esgi.al.cc1.inMemoryRepositories;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContractorInMemoryRepository implements Repository<Contractor>
{
    private final List<Contractor> contractors;

    public ContractorInMemoryRepository()
    {
        this.contractors = new ArrayList<>();
    }

    @Override
    public void create(Contractor contractor)
    {
        this.contractors.add(contractor);
    }

    @Override
    public Stream<Contractor> read()
    {
        return this.contractors.stream();
    }

    @Override
    public Contractor read(Id id)
    {
        return this.findById(id);
    }

    @Override
    public void update(Id id, Contractor contractor)
    {
        Contractor registeredContractor = this.findById(id);

        if (registeredContractor == null)
        {
            return;
        }

        this.contractors.remove(registeredContractor);

        registeredContractor.setPassword(contractor.getPassword());
        registeredContractor.setName(contractor.getName());
        registeredContractor.setPaymentMethod(contractor.getPaymentMethod());
        registeredContractor.setPaymentValidated(contractor.isPaymentValidated());

        this.contractors.add(registeredContractor);
    }

    @Override
    public void remove(Id id)
    {
        this.contractors.remove(this.findById(id));
    }

    @Override
    public boolean exists(Id id)
    {
        return this.contractors.stream().anyMatch(contractor -> contractor.getId().equals(id));
    }

    private Contractor findById(Id id)
    {
        return this.contractors.stream()
                .filter(contractor -> contractor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
