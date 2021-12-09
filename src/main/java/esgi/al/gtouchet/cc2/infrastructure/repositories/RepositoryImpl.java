package esgi.al.gtouchet.cc2.infrastructure.repositories;

import esgi.al.gtouchet.cc2.domain.models.Entity;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.infrastructure.utilitaries.JsonDataAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositoryImpl<T extends Entity> extends MemoryRepositoryImpl<T>
{
    private final JsonDataAccessor<T> jsonDataAccessor;

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
        super.create(entity);
        this.writeJsonFile();
    }

    @Override
    public void update(Id id, T entity) throws EntityNotFoundException
    {
        super.update(id, entity);
        this.writeJsonFile();
    }

    @Override
    public void remove(Id id) throws EntityNotFoundException
    {
        super.remove(id);
        this.writeJsonFile();
    }
}
