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

public class CommandProcessorTests
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
    public void unrecognizedCommand()
    {
        this.commandProcessor.process("hello");

        assertEquals(this.output.toString(), "Unrecognized command [HELLO]\r\n");
    }

    @Test
    public void recognizedCommand_upperAndLowerCase()
    {
        this.commandProcessor.process("SelECtWoRKEr");

        assertNotEquals(this.output.toString(), "Unrecognized command [SelECtWoRKEr]\r\n");
    }

    @Test
    public void helpCommand()
    {
        this.commandProcessor.process("help");

        assertEquals(this.output.toString().split("\n").length, 26 + 4); // 26 commands + 4 line breaks
    }

    @Test
    public void helpCommand_updateWorker()
    {
        this.commandProcessor.process("help updateworker");

        assertEquals(this.output.toString(), "Usage: UPDATEWORKER id newPassword newName newService newDepartment\r\n");
    }
}
