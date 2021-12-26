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

public class ReadQueriesTests
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
    public void selectAllEntityCommand_noEntityFound()
    {
        this.commandProcessor.process("selectcontractor");
        this.commandProcessor.process("selectpayment");
        this.commandProcessor.process("selectproject");
        this.commandProcessor.process("selectworker");


        assertEquals(this.output.toString(),
                "No Contractor registered yet\r\n" +
                        "No Payment registered yet\r\n" +
                        "No Project registered yet\r\n" +
                        "No Worker registered yet\r\n"
        );
    }

    @Test
    public void selectEntityIdCommand_entityNotFound()
    {
        this.commandProcessor.process("selectcontractor 1234");
        this.commandProcessor.process("selectpayment 5678");
        this.commandProcessor.process("selectproject hello");
        this.commandProcessor.process("selectworker world");

        assertEquals(this.output.toString(),
                "Error: no desired entity registered with ID [1234]\r\n" +
                        "Error: no desired entity registered with ID [5678]\r\n" +
                        "Error: no desired entity registered with ID [hello]\r\n" +
                        "Error: no desired entity registered with ID [world]\r\n"
        );
    }

    @Test
    public void selectAllEntityCommand_entitiesFound()
    {
        this.commandProcessor.process("createworker myLogin1 myPass123! guillaume plumber 91");
        this.commandProcessor.process("createworker myLogin2 myPass123! guillaume plumber 75");

        ByteArrayOutputStream afterCreationOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(afterCreationOutput));

        this.commandProcessor.process("selectworker");

        assertTrue(afterCreationOutput.toString().contains("Login: myLogin1"));
        assertTrue(afterCreationOutput.toString().contains("Login: myLogin2"));
    }

    @Test
    public void selectEntityIdCommand_entityFound()
    {
        this.commandProcessor.process("createcontractor myLogin myPass123! guillaume card");

        ByteArrayOutputStream afterCreationOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(afterCreationOutput));

        this.commandProcessor.process("selectcontractor");

        assertTrue(afterCreationOutput.toString().contains(
                "Login: myLogin\n" +
                "Password: myPass123!\n" +
                "Name: guillaume\n" +
                "Payment method: card\n" +
                "Is payment validated: false")
        );
    }
}
