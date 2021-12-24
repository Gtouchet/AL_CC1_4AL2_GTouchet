package al.cc2.gtouchet.console.handlers.payment;

import al.cc2.gtouchet.application.kernel.QueryHandler;
import al.cc2.gtouchet.application.services.dtos.payment.ReadAllPaymentQuery;
import al.cc2.gtouchet.application.services.dtos.payment.ReadPaymentQuery;
import al.cc2.gtouchet.console.engine.ConsoleCommand;
import al.cc2.gtouchet.console.engine.WrongNumberOfArgumentException;
import al.cc2.gtouchet.console.handlers.ConsoleHandler;
import al.cc2.gtouchet.domain.models.Payment;
import al.cc2.gtouchet.domain.valueObjects.Id;

import java.util.List;
import java.util.Objects;

public class ReadPaymentConsoleHandler implements ConsoleHandler
{
    private final QueryHandler<List<Payment>, ReadAllPaymentQuery> queryHandlerAll;
    private final QueryHandler<Payment, ReadPaymentQuery> queryHandlerId;

    public ReadPaymentConsoleHandler(
            QueryHandler queryHandlerAll,
            QueryHandler queryHandlerId) throws NullPointerException
    {
        this.queryHandlerAll = Objects.requireNonNull(queryHandlerAll);
        this.queryHandlerId = Objects.requireNonNull(queryHandlerId);
    }

    @Override
    public void handle(String[] params) throws WrongNumberOfArgumentException
    {
        if (params.length == ConsoleCommand.READ_PAYMENT.parameters)
        {
            List<Payment> payments = this.queryHandlerAll.handle(null);
            if (payments.size() > 0)
            {
                payments.forEach(System.out::println);
            }
            else
            {
                System.out.println("No Contractor registered yet");
            }
        }
        else if (params.length == ConsoleCommand.READ_PAYMENT.parameters + 1) // Accepts an ID as an overloaded parameter
        {
            Payment payment = this.queryHandlerId.handle(new ReadPaymentQuery(
                    Id.fromString(params[1].toLowerCase())
            ));
            if (payment != null)
            {
                System.out.println(payment);
            }
        }
        else
        {
            throw new WrongNumberOfArgumentException(ConsoleCommand.READ_PAYMENT);
        }
    }
}