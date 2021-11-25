package esgi.al.cc1.repositories;

import esgi.al.cc1.exceptions.repositoriesExceptions.ElementNotFound;
import esgi.al.cc1.exceptions.repositoriesExceptions.FailedToCreate;
import esgi.al.cc1.services.jsonServices.JsonAccessor;
import esgi.al.cc1.models.Tradesman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UserRepository implements Repository<Tradesman>
{
    private final List<Tradesman> tradesmen;

    private final JsonAccessor<Tradesman> jsonAccessor;

    public UserRepository(JsonAccessor<Tradesman> jsonAccessor)
    {
        this.jsonAccessor = jsonAccessor;

        this.tradesmen = this.getDataFromJsonFile();
    }

    private List<Tradesman> getDataFromJsonFile()
    {
        return new ArrayList<>(Arrays.asList(this.jsonAccessor.getDataFromFile()));
    }

    @Override
    public void create(Tradesman tradesman) throws FailedToCreate
    {
        Tradesman registeredTradesman = this.findUserByLogin(tradesman.getLogin());
        if (registeredTradesman != null)
        {
            throw new FailedToCreate(tradesman.getLogin(), registeredTradesman.getId());
        }

        this.tradesmen.add(tradesman);

        this.jsonAccessor.writeInFile(this.tradesmen);
    }

    @Override
    public Stream<Tradesman> read()
    {
        return this.tradesmen.stream();
    }

    @Override
    public Tradesman read(String id) throws ElementNotFound
    {
        return this.findUserById(id);
    }

    @Override
    public void update(Tradesman tradesman) throws ElementNotFound, FailedToCreate
    {
        Tradesman registeredTradesmanId = this.findUserById(tradesman.getId());

        Tradesman registeredTradesmanLogin = this.findUserByLogin(tradesman.getLogin());
        if (registeredTradesmanLogin != null && !registeredTradesmanLogin.getLogin().equals(tradesman.getLogin()))
        {
            throw new FailedToCreate(tradesman.getLogin(), registeredTradesmanLogin.getId());
        }

        this.tradesmen.remove(registeredTradesmanId);
        this.tradesmen.add(tradesman);

        this.jsonAccessor.writeInFile(this.tradesmen);
    }

    @Override
    public void remove(String id) throws ElementNotFound
    {
        Tradesman tradesman = this.findUserById(id);

        this.tradesmen.remove(tradesman);

        this.jsonAccessor.writeInFile(this.tradesmen);
    }

    private Tradesman findUserById(String id) throws ElementNotFound
    {
        Tradesman tradesman = this.tradesmen.stream()
                .filter(streamUser -> streamUser.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (tradesman == null)
        {
            throw new ElementNotFound(id);
        }

        return tradesman;
    }

    private Tradesman findUserByLogin(String login)
    {
        return this.tradesmen.stream()
                .filter(streamUser -> streamUser.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }
}
