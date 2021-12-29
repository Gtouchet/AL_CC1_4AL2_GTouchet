package al.cc2.gtouchet.console.handlers.contractor;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.ReadAllContractorQuery;
import al.cc2.gtouchet.application.services.dtos.contractor.ReadContractorQuery;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.valueObjects.EntityId;

import java.util.List;
import java.util.Objects;

public final class ReadContractorConsoleHandler implements ConsoleHandler
{
    private final QueryHandler<List<Contractor>, ReadAllContractorQuery> queryHandlerAll;
    private final QueryHandler<Contractor, ReadContractorQuery> queryHandlerId;

    public ReadContractorConsoleHandler(
            QueryHandler queryHandlerAll,
            QueryHandler queryHandlerId) throws NullPointerException
    {
        this.queryHandlerAll = Objects.requireNonNull(queryHandlerAll);
        this.queryHandlerId = Objects.requireNonNull(queryHandlerId);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.READ_CONTRACTOR.parameters)
        {
            List<Contractor> contractors = this.queryHandlerAll.handle(null);
            if (contractors.size() > 0)
            {
                contractors.forEach(System.out::println);
            }
            else
            {
                System.out.println("No Contractor registered yet");
            }
        }
        else if (params.length == ConsoleCommand.READ_CONTRACTOR.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            Contractor contractor = this.queryHandlerId.handle(new ReadContractorQuery(
                    EntityId.fromString(params[1].toLowerCase())
            ));
            if (contractor != null)
            {
                System.out.println(contractor);
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.READ_CONTRACTOR);
        }
    }
}
