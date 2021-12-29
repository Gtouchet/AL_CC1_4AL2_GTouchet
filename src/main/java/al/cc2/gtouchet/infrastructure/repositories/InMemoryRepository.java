package al.cc2.gtouchet.infrastructure.repositories;

import al.cc2.gtouchet.domain.models.Entity;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InMemoryRepository<T extends Entity> implements Repository<T>
{
    protected List<T> entities;

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
    public T read(EntityId id) throws EntityNotFoundException
    {
        return this.findById(id);
    }

    @Override
    public void update(EntityId id, T entity) throws EntityNotFoundException
    {
        this.entities.remove(this.findById(id));
        this.entities.add(entity);
    }

    @Override
    public void remove(EntityId id) throws EntityNotFoundException
    {
        this.entities.remove(this.findById(id));
    }

    @Override
    public boolean exists(EntityId id)
    {
        return this.entities.stream().anyMatch(entity -> entity.getId().equals(id));
    }

    private T findById(EntityId id) throws EntityNotFoundException
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
