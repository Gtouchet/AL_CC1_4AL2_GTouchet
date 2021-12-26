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

public class UpdateCommandsTests
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
    public void createContractor_updateContractor_readContractor()
    {
        this.commandProcessor.process("createcontractor myLogin myPass123! guillaume card");
        String contractorId = this.output.toString()
                .split("\n")[0]
                .split(" ")[1];

        ByteArrayOutputStream afterUpdateOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(afterUpdateOutput));

        this.commandProcessor.process("updatecontractor " + contractorId + " newPass789? touchet paypal");

        assertTrue(afterUpdateOutput.toString().contains(
                "Login: myLogin\n" +
                "Password: newPass789?\n" +
                "Name: touchet\n" +
                "Payment method: paypal\n" +
                "Is payment validated: false")
        );
    }
}
