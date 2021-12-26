package al.cc2.gtouchet.commandProcessor;

import al.cc2.gtouchet.application.services.HandlersContainer;
import al.cc2.gtouchet.console.engine.CommandProcessor;
import al.cc2.gtouchet.domain.validators.PasswordValidator;
import al.cc2.gtouchet.domain.valueObjects.Id;
import al.cc2.gtouchet.infrastructure.apis.PaymentMethodValidatorApi;
import al.cc2.gtouchet.infrastructure.repositories.factories.MemoryRepositoriesRetainer;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DeleteCommandsTests
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
    public void deleteNonExistentProject()
    {
        Id projectId = Id.generate();
        this.commandProcessor.process("deleteproject " + projectId);

        assertEquals(this.output.toString(), "Error: no desired entity registered with ID [" + projectId + "]\r\n");
    }
}
