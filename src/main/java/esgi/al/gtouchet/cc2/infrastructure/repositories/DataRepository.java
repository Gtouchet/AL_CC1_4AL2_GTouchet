package esgi.al.gtouchet.cc2.infrastructure.repositories;

import esgi.al.gtouchet.cc2.domain.models.Entity;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.dataAccessors.DataAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataRepository<T extends Entity> extends MemoryRepository<T>
{
    private final DataAccessor<T> dataAccessor;

    public DataRepository(DataAccessor<T> dataAccessor)
    {
        this.dataAccessor = dataAccessor;
        this.entities = this.getData();
    }

    private List<T> getData()
    {
        return new ArrayList<>(Arrays.asList(this.dataAccessor.getData()));
    }

    @Override
    public void create(T entity)
    {
        super.create(entity);

        this.dataAccessor.write(this.entities);
    }

    @Override
    public void update(Id id, T entity) throws EntityNotFoundException
    {
        super.update(id, entity);

        this.dataAccessor.write(this.entities);
    }

    @Override
    public void remove(Id id) throws EntityNotFoundException
    {
        super.remove(id);

        this.dataAccessor.write(this.entities);
    }
}
