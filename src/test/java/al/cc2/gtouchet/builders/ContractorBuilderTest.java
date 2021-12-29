package al.cc2.gtouchet.builders;

import al.cc2.gtouchet.domain.builders.ContractorBuilder;
import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.payment.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;
import al.cc2.gtouchet.domain.valueObjects.Password;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ContractorBuilderTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void buildContractor()
    {
        EntityId id = EntityId.generate();

        Contractor contractor = ContractorBuilder.init(id, "GTouchet", Clock.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                .setPaymentMethod(PaymentMethod.CARD)
                .setIsPaymentValidated(false)
                .build();

        assertNotNull(contractor);

        assertEquals(id, contractor.getId());
        assertEquals("ABcd1234!", contractor.getCredentials().getPassword().toString());
        assertEquals("Guillaume", contractor.getName());
        assertEquals(PaymentMethod.CARD, contractor.getPaymentMethod());
        assertFalse(contractor.isPaymentValidated());
    }

    @Test
    public void buildContractor_withoutPaymentValidation()
    {
        Contractor contractor = ContractorBuilder.init(EntityId.generate(), "GTouchet", Clock.now())
                .setPassword(Password.of("ABcd1234!"))
                .setName("Guillaume")
                .setPaymentMethod(PaymentMethod.CARD)
                // Payment validation set to Boolean's default if not specified (false)
                .build();

        assertNotNull(contractor);

        assertFalse(contractor.isPaymentValidated());
    }

    @Test
    public void buildContractor_missingOneProperty()
    {
        exception.expect(NullPointerException.class);

        ContractorBuilder.init(EntityId.generate(), "GTouchet", Clock.now())
                .setPassword(Password.of("ABcd1234!"))
                //.setName("Guillaume")
                .setPaymentMethod(PaymentMethod.CARD)
                .setIsPaymentValidated(false)
                .build();
    }
}
