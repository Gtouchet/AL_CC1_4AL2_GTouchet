package esgi.al.gtouchet.cc2.buildersTests;

import esgi.al.gtouchet.cc2.domain.builders.ContractorBuilder;
import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.valueObjects.Date;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ContractorBuilderTests
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void buildContractor()
    {
        Id id = Id.generate();

        Contractor contractor = ContractorBuilder.init(id, "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                .setPaymentMethod(PaymentMethod.card)
                .setIsPaymentValidated(false)
                .build();

        assertNotNull(contractor);

        assertEquals(id, contractor.getId());
        assertEquals("ABcd1234!", contractor.getPassword().toString());
        assertEquals("Guillaume", contractor.getName());
        assertEquals(PaymentMethod.card, contractor.getPaymentMethod());
        assertFalse(contractor.isPaymentValidated());
    }

    @Test
    public void buildContractor_withoutPaymentValidation()
    {
        Contractor contractor = ContractorBuilder.init(Id.generate(), "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                .setPaymentMethod(PaymentMethod.card)
                // Payment validation set to Boolean's default if not specified (false)
                .build();

        assertNotNull(contractor);

        assertFalse(contractor.isPaymentValidated());
    }

    @Test
    public void buildContractor_missingOneProperty()
    {
        exception.expect(NullPointerException.class);

        ContractorBuilder.init(Id.generate(), "GTouchet", Date.now())
                .setPassword(Password.of("ABcd1234!"))
                //.setName("Guillaume")
                .setPaymentMethod(PaymentMethod.card)
                .setIsPaymentValidated(false)
                .build();
    }
}
