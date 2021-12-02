package esgi.al.cc1;

import esgi.al.cc1.domain.models.Entity;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.infrastructure.repositories.EntityNotFoundException;
import esgi.al.cc1.infrastructure.repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InMemoryRepository<T extends Entity> implements Repository<T>
{
    private final List<T> entities;

    public InMemoryRepository()
    {
        this.entities = new ArrayList<>();
    }

    @Override
    public void create(T entity)
    {
        this.entities.add(entity);
    }

    @Override
    public Stream<T> read()
    {
        return this.entities.stream();
    }

    @Override
    public T read(Id id) throws EntityNotFoundException
    {
        return this.findById(id);
    }

    @Override
    public void update(Id id, T entity) throws EntityNotFoundException
    {
        this.entities.remove(this.findById(id));
        this.entities.add(entity);
    }

    @Override
    public void remove(Id id) throws EntityNotFoundException
    {
        this.entities.remove(this.findById(id));
    }

    @Override
    public boolean exists(Id id)
    {
        return this.entities.stream().anyMatch(entity -> entity.getId().equals(id));
    }

    private T findById(Id id) throws EntityNotFoundException
    {
        if (this.exists(id))
        {
            return this.entities.stream()
                    .filter(entity -> entity.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }
        else
        {
            throw new EntityNotFoundException(id);
        }
    }
}
