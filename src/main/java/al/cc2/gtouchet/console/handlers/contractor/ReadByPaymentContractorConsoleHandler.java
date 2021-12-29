package al.cc2.gtouchet.console.handlers.contractor;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.contractor.ReadByPaymentContractorQuery;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.payment.PaymentMethod;

import java.util.List;
import java.util.Objects;

public final class ReadByPaymentContractorConsoleHandler implements ConsoleHandler
{
    private final QueryHandler<List<Contractor>, ReadByPaymentContractorQuery> queryHandler;

    public ReadByPaymentContractorConsoleHandler(QueryHandler queryHandler) throws NullPointerException
    {
        this.queryHandler = Objects.requireNonNull(queryHandler);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.READ_CONTRACTOR_BY_PAYMENT.parameters)
        {
            try {
                List<Contractor> contractors = this.queryHandler.handle(new ReadByPaymentContractorQuery(
                        PaymentMethod.valueOf(params[1].toLowerCase())
                ));
                if (contractors.size() > 0)
                {
                    contractors.forEach(System.out::println);
                }
                else
                {
                    System.out.println("No Contractor registered with payment method [" + params[1].toLowerCase() + "]");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: unknown payment method [" + params[4] + "]");
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.READ_CONTRACTOR_BY_PAYMENT);
        }
    }
}
