package al.cc2.gtouchet.infrastructure.repositories;

import al.cc2.gtouchet.domain.models.Entity;
import al.cc2.gtouchet.domain.valueObjects.EntityId;
import al.cc2.gtouchet.infrastructure.dataAccessors.DataAccessor;

import java.util.List;

public final class DataRepository<T extends Entity> extends InMemoryRepository<T>
{
    private final DataAccessor<T> dataAccessor;

    public DataRepository(DataAccessor<T> dataAccessor)
    {
        this.dataAccessor = dataAccessor;
        this.entities = this.getData();
    }

    private List<T> getData()
    {
        return this.dataAccessor.getData();
    }

    @Override
    public void create(T entity)
    {
        super.create(entity);

        this.dataAccessor.write(this.entities);
    }

    @Override
    public void update(EntityId id, T entity) throws EntityNotFoundException
    {
        super.update(id, entity);

        this.dataAccessor.write(this.entities);
    }

    @Override
    public void remove(EntityId id) throws EntityNotFoundException
    {
        super.remove(id);

        this.dataAccessor.write(this.entities);
    }
}
