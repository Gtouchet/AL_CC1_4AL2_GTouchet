package al.cc2.gtouchet.commandProcessor;

import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.console.engine.CommandProcessor;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.factories.MemoryRepositoriesRetainer;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CreateCommandsTests
{
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();;

    private CommandProcessor commandProcessor;

    @Before
    public void setup()
    {
        System.setOut(new PrintStream(this.output));

        HandlersContainer handlersContainer = HandlersContainer.initialize(
                new MemoryRepositoriesRetainer(),
                new PasswordValidator(),
                new PaymentMethodValidatorApi()
        );
        this.commandProcessor = new CommandProcessor(handlersContainer);
    }

    @Test
    public void createWorker_wrongNumberOfArguments()
    {
        this.commandProcessor.process("createworker myLogin myPass");

        assertEquals(this.output.toString(),
                "Error: wrong number of argument for command [CREATEWORKER]\n" +
                        "CREATEWORKER login password name service department\r\n"

        );
    }

    @Test
    public void createContractor_invalidPaymentMethod()
    {
        this.commandProcessor.process("createcontractor myLogin myPass123! guillaume jePayeCash");

        assertEquals(this.output.toString(), "Error: unknown payment method [jePayeCash]\r\n");
    }

    @Test
    public void createWorker_readWorkerProperties()
    {
        this.commandProcessor.process("createworker myLogin myPass123! guillaume plumber 75");

        assertTrue(this.output.toString().contains(
                "Login: myLogin\n" +
                "Password: myPass123!\n" +
                "Name: guillaume\n" +
                "Service: PLUMBER\n" +
                "Department: 75")
        );
    }
}
