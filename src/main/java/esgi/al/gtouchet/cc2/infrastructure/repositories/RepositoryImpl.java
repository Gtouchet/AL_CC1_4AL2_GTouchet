package esgi.al.gtouchet.cc2.infrastructure.repositories;

import esgi.al.gtouchet.cc2.domain.models.Entity;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.utilitaries.JsonDataAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RepositoryImpl<T extends Entity> implements Repository<T>
{
    private final JsonDataAccessor<T> jsonDataAccessor;
    private final List<T> entities;

    public RepositoryImpl(JsonDataAccessor<T> jsonDataAccessor)
    {
        this.jsonDataAccessor = jsonDataAccessor;
        this.entities = this.getDataFromJsonFile();
    }

    private List<T> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonDataAccessor.getDataFromFile()));
    }

    private void writeJsonFile()
    {
        this.jsonDataAccessor.writeInFile(this.entities);
    }

    @Override
    public void create(T entity)
    {
        this.entities.add(entity);
        this.writeJsonFile();
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
        this.writeJsonFile();
    }

    @Override
    public void remove(Id id) throws EntityNotFoundException
    {
        this.entities.remove(this.findById(id));
        this.writeJsonFile();
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
